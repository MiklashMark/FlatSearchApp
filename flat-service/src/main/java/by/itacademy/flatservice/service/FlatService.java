package by.itacademy.flatservice.service;

import by.itacademy.exceptions.dto.flat.FlatDTO;
import by.itacademy.exceptions.dto.flat.FlatPageDTO;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.EntityNotFoundException;
import by.itacademy.flatservice.core.EntityDTOMapper;
import by.itacademy.flatservice.core.FlatFilter;
import by.itacademy.flatservice.repository.api.IFlatRepository;
import by.itacademy.flatservice.repository.entity.Flat;
import by.itacademy.flatservice.service.api.IFlatService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlatService implements IFlatService {
    private final IFlatRepository flatRepository;

    public FlatService(IFlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    @Override
    public FlatPageDTO getPage(FlatFilter filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());
        Page<Flat> flatPage = flatRepository.findAll(getFilter(filter), pageable);
        checkIfFlatPageIsEmpty(flatPage);
        return buildAuditPageDTO(flatPage);
    }


    private Specification<Flat> getFilter(FlatFilter filter) {
        return new Specification<Flat>() {
            @Override
            public Predicate toPredicate(Root<Flat> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
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
            }
        };
    }


    private void checkIfFlatPageIsEmpty(Page<Flat> flatPage) {
        if (flatPage.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessages.DATA_NOT_FOUND.getMessage());
        }
    }

    private FlatPageDTO buildAuditPageDTO(Page<Flat> flatPage) {
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
