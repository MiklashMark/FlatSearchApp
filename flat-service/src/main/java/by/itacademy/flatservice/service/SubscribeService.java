package by.itacademy.flatservice.service;

import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.DuplicateEntityException;
import by.itacademy.exceptions.exception.custom_exceptions.EntityNotFoundException;
import by.itacademy.exceptions.exception.custom_exceptions.InternalServerException;
import by.itacademy.flatservice.core.EntityDTOMapper;
import by.itacademy.flatservice.core.SubscribeDTO;
import by.itacademy.flatservice.core.SubscribeFilterDTO;
import by.itacademy.flatservice.repository.api.ISubscribeRepository;
import by.itacademy.flatservice.repository.subscribe.Subscribe;
import by.itacademy.flatservice.repository.subscribe.SubscribeFilter;
import by.itacademy.flatservice.service.api.ISubscribeService;
import by.itacademy.flatservice.service.context.UserHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class SubscribeService implements ISubscribeService {
    private final ISubscribeRepository subscribeRepository;
    private final UserHolder userHolder;

    public SubscribeService(ISubscribeRepository subscribeRepository,
                            UserHolder userHolder) {
        this.subscribeRepository = subscribeRepository;
        this.userHolder = userHolder;
    }

    @Override
    @Transactional
    public void add(SubscribeFilterDTO subscribeFilterDTO) {
        UserDTO userDTO = userHolder.getUserInfo();
        Optional<Subscribe> subscribeOptional = subscribeRepository.findSubscribeByUserUuid(userDTO.getUuid());
        SubscribeFilter newSubscribeFilter = EntityDTOMapper.INSTANCE.toSubscribeFilter(subscribeFilterDTO);

        Subscribe subscribe = subscribeOptional.map(existingSubscribe -> {
            boolean filterExists = existingSubscribe.getSubscribeFilters().stream()
                    .anyMatch(filter -> filter.equals(newSubscribeFilter));

            if (filterExists) {
                throw new DuplicateEntityException(ErrorMessages.SUBSCRIBE_ALREADY_ADDED.getMessage());
            }

            existingSubscribe.setSubscribeFilters(newSubscribeFilter);
            existingSubscribe.setDtUpdate(LocalDateTime.now());
            return existingSubscribe;
        }).orElseGet(() -> {
            Subscribe newSubscribe = new Subscribe();
            newSubscribe.setUserUuid(userDTO.getUuid());
            newSubscribe.setDtCreate(LocalDateTime.now());
            newSubscribe.setDtUpdate(LocalDateTime.now());
            newSubscribe.setSubscribeFilters(EntityDTOMapper.INSTANCE.toSubscribeFilter(subscribeFilterDTO));
            return newSubscribe;
        });

        try {
            subscribeRepository.save(subscribe);
        } catch (DataAccessException e) {
            throw new InternalServerException(ErrorMessages.SERVER_ERROR.getMessage());
        }
    }


    @Override
    public SubscribeDTO get() {
        UserDTO userDTO = userHolder.getUserInfo();
        Subscribe subscribe = subscribeRepository.findSubscribeByUserUuid(userDTO.getUuid())
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.NO_SUBSCRIPTIONS_FOUND.getMessage()));
        return EntityDTOMapper.INSTANCE.toSubscribeDTO(subscribe);
    }

}
