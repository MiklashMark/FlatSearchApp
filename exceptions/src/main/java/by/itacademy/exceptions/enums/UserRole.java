package by.itacademy.exceptions.enums;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER"),
    MANAGER("MANAGER"),
    SYSTEM("SYSTEM");

    private final String value;
    UserRole(String value) {
       this.value = value;
    }

    public String getValue() {
        return value;
    }
}
