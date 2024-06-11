package by.itacademy.flatservice.contoller;

import by.itacademy.exceptions.dto.flat.bookmark.BookmarkDTO;
import by.itacademy.exceptions.enums.messages.Messages;
import by.itacademy.flatservice.service.api.IBookmarkService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class BookmarkRestController {

    private final IBookmarkService bookmarkService;

    public BookmarkRestController(IBookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("flats/{uuid}/bookmark")
    public ResponseEntity<String> add(@PathVariable UUID uuid) {
        bookmarkService.add(uuid);
        return ResponseEntity.ok(Messages.BOOKMARK_SUCCESSFULLY_ADDED.getMessage());
    }

    @DeleteMapping("flats/{uuid}/bookmark")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) {
        bookmarkService.delete(uuid);
        return ResponseEntity.ok(Messages.BOOKMARK_SUCCESSFULLY_DELETED.getMessage());
    }

    @GetMapping("/bookmark")
    public ResponseEntity<BookmarkDTO> get(
            @PageableDefault(size = 20) Pageable pageable) {
        return  ResponseEntity.ok(bookmarkService.get(pageable));
    }

}
