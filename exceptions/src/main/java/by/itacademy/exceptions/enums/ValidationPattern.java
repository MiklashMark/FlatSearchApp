package by.itacademy.exceptions.enums;

import lombok.Getter;

@Getter
public enum ValidationPattern {
    EMAIL("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"),
    PASSWORD("^(?=.*[A-Za-zА-Яа-я])(?=.*\\d)(?=.*[@#$%^&+=]).*$"),
    FIO("^[A-Za-zА-Яа-яЁё]+([ -][A-Za-zА-Яа-яЁё]+)* [A-Za-zА-Яа-яЁё]+([ -][A-Za-zА-Яа-яЁё]+)* [A-Za-zА-Яа-яЁё]+([ -][A-Za-zА-Яа-яЁё]+)*$");

    private final String pattern;

    ValidationPattern(String pattern) {
        this.pattern = pattern;
    }

}

