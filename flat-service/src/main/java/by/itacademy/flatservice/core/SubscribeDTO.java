package by.itacademy.flatservice.core;

import by.itacademy.flatservice.repository.subscribe.SubscribeFilter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SubscribeDTO {
    private UUID uuid;
    private UUID userUuid;
    private long dtCreate;
    private long dtUpdate;
    private List<SubscribeFilterDTO> subscribeFilters;
}
