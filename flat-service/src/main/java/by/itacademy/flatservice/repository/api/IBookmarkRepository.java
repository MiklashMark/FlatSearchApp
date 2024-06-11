package by.itacademy.flatservice.repository.api;

import by.itacademy.flatservice.repository.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IBookmarkRepository extends JpaRepository<Bookmark, UUID> {
    void deleteBookmarkByUserUuidAndFlatUuid(UUID userUuid, UUID flatUuid);
    Optional<Page<Bookmark>> getBookmarksByUserUuid(UUID userUuid, Pageable pageable);
}
