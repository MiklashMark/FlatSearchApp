package by.itacademy.exceptions.dto.flat;

import lombok.Getter;

@Getter
public enum OfferType {
    RENT("https://realt.by/belarus/rent/flat-for-long/"),

    RENT_FOR_DAY("https://realt.by/belarus/rent/flat-for-day/"),
    SALE("https://realt.by/belarus/sale/flats/");

    private final String value;
    OfferType(String value) {
        this.value = value;
    }
}
