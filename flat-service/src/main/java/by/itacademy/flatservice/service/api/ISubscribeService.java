package by.itacademy.flatservice.service.api;

import by.itacademy.flatservice.core.SubscribeDTO;
import by.itacademy.flatservice.core.SubscribeFilterDTO;

public interface ISubscribeService {
    void add(SubscribeFilterDTO subscribeFilterDTO);
    SubscribeDTO get();

}
