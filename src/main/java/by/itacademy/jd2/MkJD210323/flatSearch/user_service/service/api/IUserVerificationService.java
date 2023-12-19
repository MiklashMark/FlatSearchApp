package by.itacademy.jd2.MkJD210323.flatSearch.user_service.service.api;

import by.itacademy.jd2.MkJD210323.flatSearch.user_service.dao.entity.User;
import org.springframework.cloud.openfeign.FeignClient;

public interface IUserVerificationService {
    
    void verify(User user);
}
