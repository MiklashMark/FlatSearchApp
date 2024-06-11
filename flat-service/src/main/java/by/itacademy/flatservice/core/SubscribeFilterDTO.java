package by.itacademy.flatservice.core;

import by.itacademy.exceptions.dto.flat.OfferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SubscribeFilterDTO {
    private int page;
    private int size;
    private OfferType offerType;
    private int priceFrom;
    private int priceTo;
    private int bedroomsFrom;
    private int bedroomsTo;
    private int areaFrom;
    private int areaTo;
    private List<Integer> floors;
    private boolean photo;
}
