package by.itacademy.flatservice.service;

import by.itacademy.exceptions.dto.flat.FlatDTO;
import by.itacademy.exceptions.dto.flat.FlatPageDTO;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.EntityNotFoundException;
import by.itacademy.flatservice.core.EntityDTOMapper;
import by.itacademy.flatservice.core.FlatFilter;
import by.itacademy.flatservice.core.SubscribeFilterDTO;
import by.itacademy.flatservice.repository.api.IFlatRepository;
import by.itacademy.flatservice.repository.entity.flat.Flat;
import by.itacademy.flatservice.service.api.IFlatService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FlatService implements IFlatService {
    private final IFlatRepository flatRepository;
    private final static int PAGE = 0;
    private final static int SIZE = 20;

    public FlatService(IFlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    @Override
    public FlatPageDTO getPage(FlatFilter flatFilter) {
        Pageable pageable = PageRequest.of(flatFilter.getPage(), flatFilter.getSize());
        Specification<Flat> filter = getFilter(flatFilter);
        Page<Flat> flatPage = flatRepository.findAll(filter, pageable);
        checkIfFlatPageIsEmpty(flatPage);
        return buildFlatPageDTO(flatPage);
    }

    @Override
    public Flat get(UUID uuid) {
        return flatRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.DATA_NOT_FOUND.getMessage()));
    }


    @Override
    public Specification<Flat> getFilter(FlatFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.between(root.get("price"), filter.getPriceFrom(), filter.getPriceTo()));
            predicates.add(cb.between(root.get("bedrooms"), filter.getBedroomsFrom(), filter.getBedroomsTo()));
            predicates.add(cb.between(root.get("area"), filter.getAreaFrom(), filter.getAreaTo()));
            if (filter.isPhoto()) {
                predicates.add(cb.isNotNull(root.get("photos")));
            }
            if (!filter.getFloors().isEmpty()) {
                predicates.add((root.get("floor")).in(filter.getFloors()));
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }


    private void checkIfFlatPageIsEmpty(Page<Flat> flatPage) {
        if (flatPage.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessages.DATA_NOT_FOUND.getMessage());
        }
    }

    private FlatPageDTO buildFlatPageDTO(Page<Flat> flatPage) {
        FlatPageDTO flatPageDTO = new FlatPageDTO();

        flatPageDTO.setSize(flatPage.getSize());
        flatPageDTO.setNumber(flatPage.getNumber());
        flatPageDTO.setTotalPages(flatPage.getTotalPages());
        flatPageDTO.setTotalElements(flatPage.getTotalElements());
        flatPageDTO.setNumberOfElements(flatPage.getNumberOfElements());
        flatPageDTO.setFirst(flatPage.isFirst());
        flatPageDTO.setLast(flatPage.isLast());

        List<FlatDTO> reportDTOS = EntityDTOMapper.INSTANCE
                .flatListToFlatDTOList(flatPage.getContent());
        flatPageDTO.setContent(reportDTOS);

        return flatPageDTO;
    }


}
