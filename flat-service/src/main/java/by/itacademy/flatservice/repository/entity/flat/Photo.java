package by.itacademy.flatservice.repository.entity.flat;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "flat", name = "photo")
public class Photo {

    @Id
    @GeneratedValue
    @Column(name = "photo_uuid")
    private UUID photoUuid;

    @Column(name = "url_photo")
    private String photoUrl;


    public Photo() {
    }

    public Photo(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public UUID getPhotoUuid() {
        return photoUuid;
    }

    public void setPhotoUuid(UUID photo_uuid) {
        this.photoUuid = photo_uuid;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo photo)) return false;
        return Objects.equals(photoUuid, photo.photoUuid) && Objects.equals(photoUrl, photo.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoUuid, photoUrl);
    }
}