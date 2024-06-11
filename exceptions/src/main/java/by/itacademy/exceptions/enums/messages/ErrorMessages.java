package by.itacademy.exceptions.enums.messages;

import by.itacademy.exceptions.enums.ErrorsTypes;
import lombok.Getter;

@Getter
public enum ErrorMessages {
    INCORRECT_MAIL_FORMAT("Incorrect mail format."),
    PASSWORD_LENGTH_REQUIREMENT("Password length should be at least 8 characters." +
            " The password must include at least letters, digits, and one or more special characters."),
    INVALID_FIO("Fio must consist of 3 words starting with a capital letter. Only letters are used."),
    ALREADY_REGISTERED("This email is already registered"),
    SERVER_ERROR("The server was unable to process the request correctly." +
            " Please contact your administrator"),
    INCORRECT_VERIFICATION_CODE("Incorrect code. Try again."),
    INCORRECT_MAIL_OR_PASSWORD("Login or password is incorrect"),
    USER_NOT_FOUND("User not found!"),
    AUDIT_NOT_FOUND("Audit not found!"),
    FIELD_IS_EMPTY("field is empty"),
    DATA_NOT_FOUND("No results found for this search."),
    ACCESS_ERROR("This authorization token is prohibited from making requests to this address"),
    AUTHENTICATION_ERROR("To complete a request to this address, you must transfer an authorization token."),
    LOCK_ERROR("Data update error. Try again."),
    UNKNOWN_ERROR("Unknown error occurred. Please, contact the administrator."),
    UNREGISTERED_ACTION("Unregistered action."),
    REPORT_ALREADY_CREATED("the report has already been created"),
    RESOURCE_NOT_FOUND("No information about this report."),
    BOOKMARK_ALREADY_ADDED("Bookmark already added"),
    INCORRECT_DATA("Incorrect data"),
    NO_SUBSCRIPTIONS_FOUND("No subscriptions found."),
    SUBSCRIBE_ALREADY_ADDED("Subscribe already added");
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    }

