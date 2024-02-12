package by.itacademy.flatservice.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FlatFilter {
    private int page;
    private int size;
    private int priceFrom;
    private int priceTo;
    private int bedroomsFrom;
    private int bedroomsTo;
    private int areaFrom;
    private int areaTo;
    private List<Integer> floors;
    private boolean photo;
}
