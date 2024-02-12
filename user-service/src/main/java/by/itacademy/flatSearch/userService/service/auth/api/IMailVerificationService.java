package by.itacademy.flatSearch.userService.service.auth.api;

import by.itacademy.flatSearch.userService.dao.entity.User;

public interface IMailVerificationService {
    public void create(User user);
}
