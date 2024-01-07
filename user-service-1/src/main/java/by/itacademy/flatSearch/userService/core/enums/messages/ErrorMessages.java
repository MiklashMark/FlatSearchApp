package by.itacademy.flatSearch.userService.core.enums.messages;

public enum ErrorMessages {
    INCORRECT_MAIL_FORMAT("Incorrect mail format."),
    PASSWORD_LENGTH_REQUIREMENT("Password length should be at least 8 characters." +
            " The password must include at least letters, digits, and one or more special characters."),
    INVALID_FIO("Fio must consist of 3 words starting with a capital letter. Only letters are used."),
    EMAIL_ALREADY_REGISTERED("This email is already registered"),
    FIO_ALREADY_EXISTS("A user with the same name already exists"),
    SERVER_ERROR("The server was unable to process the request correctly." +
            " Please contact your administrator"),

    INCORRECT_VERIFICATION_CODE("Incorrect code. Try again."),
    INCORRECT_MAIL_OR_PASSWORD("Login or password is incorrect"),
    USER_NOT_FOUND("User not found!"),
    MAIL_IS_EMPTY("Mail field is empty"),
    PASSWORD_IS_EMPTY("Password field is empty"),
    FIO_IS_EMPTY("Fio field is empty");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

