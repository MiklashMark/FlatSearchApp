package by.itacademy.exceptions.enums.messages;

import lombok.Getter;

@Getter
public enum Messages {
    REGISTERED_SUCCESSFULLY("Registered successfully." +
                                    " You should receive a confirmation link/code to your email."),
    VERIFIED_SUCCESSFULLY("Verification successfully."),
    USER_SUCCESSFULLY_ADDED("User successfully added."),
    ACCOUNT_IS_NOT_ACTIVATED("Account is not activated! Please verify."),
    SUCCESSFULLY_UPDATED("Successfully updated"),
    AUDIT_SUCCESSFULLY_ADDED("Audit successfully added");

    private final String message;

    Messages(String message) {
        this.message = message;
    }
}
