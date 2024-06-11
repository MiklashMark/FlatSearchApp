package by.itacademy.parserservice.service.api;

import by.itacademy.exceptions.dto.flat.OfferType;

public interface IParsingService {
    void get(OfferType offerType, int startPage, int endPage);
    int getFlatsCount(OfferType offerType);
    int getPageCount(OfferType offerType);
}
