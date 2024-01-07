package by.itacademy.flatSearch.userService.core.enums;

public enum Messages {
    INCORRECT_MAIL_FORMAT("Incorrect mail format."),
    PASSWORD_LENGTH_REQUIREMENT("Password length should be at least 8 characters." +
            " The password must include at least letters, digits, and one or more special characters."),
    INVALID_FIO("Fio must consist of 3 words starting with a capital letter. Only letters are used."),
    EMAIL_ALREADY_REGISTERED("This email is already registered"),
    FIO_ALREADY_EXISTS("A user with the same name already exists"),
    SERVER_ERROR("The server was unable to process the request correctly." +
            " Please contact your administrator"),
    REGISTERED_SUCCESSFULLY("Registered successfully." +
            " You should receive a confirmation link/code to your email."),
    INCORRECT_VERIFICATION_CODE("Incorrect code. Try again."),
    VERIFIED_SUCCESSFULLY("Verification successfully"),
    INCORRECT_MAIL_OR_PASSWORD("Login or password is incorrect"),
    USER_NOT_FOUND("User not found!"),
    USER_SUCCESSFULLY_ADDED("User successfully added.");
    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

