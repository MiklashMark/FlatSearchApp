package by.itacademy.exceptions.dto.flat.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlatField {
    BEDROOMS("Количество комнат"),
    AREA("Площадь общая"),
    FLOOR("Этаж / этажность");

    private final String value;

}