package by.itacademy.exceptions.dto.flat;

import lombok.Getter;

@Getter
public enum OfferType {
    RENT("rent-flat-for-long/object/"),

    RENT_FOR_DAY("rent-flat-for-long/object/"),
    SALE("sale-flats/object/");

    private final String value;
    OfferType(String value) {
        this.value = value;
    }
}
