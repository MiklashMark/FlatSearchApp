package by.itacademy.exceptions.dto.flat.bookmark;

import by.itacademy.exceptions.dto.flat.FlatDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BookmarkDTO {
    private int number;
    private int size;
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
    private boolean first;
    private boolean last;
    private List<FlatDTO> content;
}
