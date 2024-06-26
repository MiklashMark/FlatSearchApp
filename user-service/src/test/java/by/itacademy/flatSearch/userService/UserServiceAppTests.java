package by.itacademy.flatSearch.userService;

import by.itacademy.exceptions.error.StructuredErrorResponse;
import by.itacademy.exceptions.exception.custom_exceptions.ValidationException;
import by.itacademy.flatSearch.userService.dao.entity.User;
import by.itacademy.flatSearch.userService.service.validation.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceAppTests {
    @Mock
    private ThreadLocal<StructuredErrorResponse> structuredErrorResponseThreadLocale;

    @InjectMocks
    private ValidationService validationService;

    @Test
    public void validateSuccessfully() {
        User user = createValidUser();
        assertDoesNotThrow(() -> validationService.validateRegistration(user));

        verify(structuredErrorResponseThreadLocale, times(0)).get();
    }
    @Test
    public void validateInvalidData() {
        User user = new User();
        user.setFio("Марк");
        user.setMail("das");
        user.setPassword("aksjda");
        assertThrows(ValidationException.class,
                () -> validationService.validateRegistration(user));
    }

    public User createValidUser() {
        User user = new User();
        user.setFio("Mark Miklash Vladislavovich");
        user.setMail("markmiklash@gmail.com");
        user.setPassword("kdafskfklaskfa");
        return user;
    }
}