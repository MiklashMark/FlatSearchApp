package by.itacademy.flatSearch.userService.core.enums;

public enum ErrorFieldNames {
    MAIL("mail"),
    PASSWORD("password"),
    FIO("fio");


    private final String field;

    ErrorFieldNames(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
