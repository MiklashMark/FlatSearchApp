package by.itacademy.flatservice.service;

import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.dto.flat.FlatDTO;
import by.itacademy.exceptions.dto.flat.bookmark.BookmarkDTO;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.DuplicateEntityException;
import by.itacademy.exceptions.exception.custom_exceptions.EntityNotFoundException;
import by.itacademy.exceptions.exception.custom_exceptions.InternalServerException;
import by.itacademy.exceptions.exception.custom_exceptions.ValidationException;
import by.itacademy.flatservice.config.properites.profling.Profiling;
import by.itacademy.flatservice.core.EntityDTOMapper;
import by.itacademy.flatservice.repository.api.IBookmarkRepository;
import by.itacademy.flatservice.repository.entity.Bookmark;
import by.itacademy.flatservice.service.api.IBookmarkService;
import by.itacademy.flatservice.service.api.IFlatService;
import by.itacademy.flatservice.service.context.UserHolder;
import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Profiling
@Log4j2
@Service
@Transactional(readOnly = true)
public class BookmarkService implements IBookmarkService {
    private final IBookmarkRepository bookmarkRepository;
    private final IFlatService flatService;
    private final UserHolder userHolder;

    public BookmarkService(IBookmarkRepository bookmarkRepository, IFlatService flatService, UserHolder userHolder) {
        this.bookmarkRepository = bookmarkRepository;
        this.flatService = flatService;
        this.userHolder = userHolder;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BookmarkDTO get(Pageable pageable) {
        UserDTO userDTO = userHolder.getUserInfo();
        Page<Bookmark> bookmarkPage = bookmarkRepository.getBookmarksByUserUuid(userDTO.getUuid(), pageable)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.DATA_NOT_FOUND.getMessage()));

        List<FlatDTO> flatDTOS = bookmarkPage.getContent().stream()
                .map(bookmark -> flatService.get(bookmark.getFlatUuid()))
                .map(EntityDTOMapper.INSTANCE::flatToFlatDTO)
                .collect(Collectors.toList());

        return EntityDTOMapper.INSTANCE.createBookmarkDTO(bookmarkPage, flatDTOS);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void add(UUID flatUuid) {
        checkFlatUuid(flatUuid);
        UserDTO userDTO = userHolder.getUserInfo();
        Bookmark bookmark = new Bookmark();
        bookmark.setUserUuid(userDTO.getUuid());
        bookmark.setFlatUuid(flatUuid);
        try {
            bookmarkRepository.saveAndFlush(bookmark);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException constraintViolationException) {
                if (constraintViolationException.getMessage().contains("user_uuid")) {
                    throw new DuplicateEntityException(ErrorMessages.BOOKMARK_ALREADY_ADDED.getMessage());
                }
            }
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void delete(UUID flatUuid) {
        checkFlatUuid(flatUuid);
        UserDTO userDTO = userHolder.getUserInfo();
        try {
            bookmarkRepository.deleteBookmarkByUserUuidAndFlatUuid(userDTO.getUuid(), flatUuid);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }

    private void checkFlatUuid(UUID uuid) {
        if(flatService.get(uuid) == null) {
            throw new ValidationException(ErrorMessages.INCORRECT_DATA.getMessage());
        }
    }
}
