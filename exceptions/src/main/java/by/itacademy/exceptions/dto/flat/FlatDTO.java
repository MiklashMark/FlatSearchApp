package by.itacademy.exceptions.dto.flat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FlatDTO {
    private UUID uuid;

    private long dtCreate;

    private long dtUpdate;

    private OfferType offerType;

    private String description;

    private int area;

    private int price;

    private int bedrooms;

    private int floor;

    private List<String> photoUrls;

    private String originalUrl;
}
