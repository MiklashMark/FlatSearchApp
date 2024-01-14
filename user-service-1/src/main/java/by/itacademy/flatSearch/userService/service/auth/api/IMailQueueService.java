package by.itacademy.flatSearch.userService.service.auth.api;


import by.itacademy.flatSearch.userService.dao.entity.User;

public interface IMailQueueService {
    void addInMailQueue(User user);
}
