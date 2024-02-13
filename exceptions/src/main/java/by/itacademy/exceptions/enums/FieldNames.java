package by.itacademy.exceptions.enums;

public enum FieldNames {
    MAIL("mail"),
    PASSWORD("password"),
    FIO("fio"),
    ROLE("role");

    private final String field;

    FieldNames(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
