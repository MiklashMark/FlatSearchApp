package by.itacademy.exceptions.enums.action;

public enum Actions {
    REGISTRATION("Registration "),
    LOGIN("Login"),
    ME("Getting personal info"),
    
    ADD_USER("Add user"),
    GET_USER("Get user"),
    GET_USERS("Get users"),
    UPDATE_USER("Update user");
    private final String value;

    Actions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
