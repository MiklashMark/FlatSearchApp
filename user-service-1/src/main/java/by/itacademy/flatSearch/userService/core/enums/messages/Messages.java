package by.itacademy.flatSearch.userService.core.enums.messages;

import lombok.Getter;

@Getter
public enum Messages {
    REGISTERED_SUCCESSFULLY("Registered successfully." +
                                    " You should receive a confirmation link/code to your email."),
    VERIFIED_SUCCESSFULLY("Verification successfully"),
    USER_SUCCESSFULLY_ADDED("User successfully added.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }
}
