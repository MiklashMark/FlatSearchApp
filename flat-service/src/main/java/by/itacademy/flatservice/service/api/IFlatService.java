package by.itacademy.flatservice.service.api;

import by.itacademy.exceptions.dto.flat.FlatPageDTO;
import by.itacademy.flatservice.core.FlatFilter;

public interface IFlatService {
    FlatPageDTO getPage(FlatFilter filter);
}
