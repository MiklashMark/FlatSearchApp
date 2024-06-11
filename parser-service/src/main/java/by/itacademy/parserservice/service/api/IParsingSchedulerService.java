package by.itacademy.parserservice.service.api;

import by.itacademy.exceptions.dto.flat.OfferType;

public interface IParsingSchedulerService {
    void start();
    void getRentForLongFlats();
    void getSaleFlats();
    void getRentForDayFlats();
    void getFlat(OfferType offerType);
    void save();
}
