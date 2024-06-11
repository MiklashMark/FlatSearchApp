package by.itacademy.flatservice.service.api;

import by.itacademy.exceptions.dto.flat.bookmark.BookmarkDTO;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IBookmarkService {
    BookmarkDTO get(Pageable pageable);

    void add(UUID uuid);

    void delete(UUID uuid);
}
