package by.itacademy.flatSearch.userService.service.auth.api;

public interface IPasswordEncoders {
    boolean matches(String rawPassword, String encodedPassword);
    public String encode(String rawPassword);
}
