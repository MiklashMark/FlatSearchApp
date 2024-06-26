package by.itacademy.flatservice.repository.api;

import by.itacademy.flatservice.repository.entity.flat.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface IPhotoRepository extends JpaRepository<Photo, UUID> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Photo p1 " +
            "WHERE p1.photoUuid NOT IN (" +
            "    SELECT MIN(p2.photoUuid) " +
            "    FROM Photo p2 " +
            "    GROUP BY p2.photoUrl" +
            ")")
    void removeDuplicatePhotos();
}
