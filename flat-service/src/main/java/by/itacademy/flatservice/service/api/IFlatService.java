package by.itacademy.flatservice.service.api;

import by.itacademy.exceptions.dto.flat.FlatPageDTO;
import by.itacademy.flatservice.core.FlatFilter;
import by.itacademy.flatservice.repository.entity.flat.Flat;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface IFlatService {
    FlatPageDTO getPage(FlatFilter filter);

    Flat get(UUID uuid);


    Specification<Flat> getFilter(FlatFilter filter);


}
