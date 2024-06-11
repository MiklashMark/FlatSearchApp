package by.itacademy.flatservice.service.api;

import by.itacademy.exceptions.dto.flat.FlatPageDTO;
import by.itacademy.flatservice.core.SubscribeDTO;
import by.itacademy.flatservice.core.SubscribeFilterDTO;
import by.itacademy.flatservice.repository.subscribe.Subscribe;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISubscribeService {
    void add(SubscribeFilterDTO subscribeFilterDTO);
    SubscribeDTO get();

}
