package by.itacademy.flatservice.repository.entity;


import jakarta.persistence.*;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "flat", name = "bookmark")
@Accessors(chain = true)
public class Bookmark {
    @Id
    @GeneratedValue
    private UUID uuid;
    @Column(unique = true, name = "user_uuid")
    private UUID userUuid;

//    @ElementCollection
//    @CollectionTable(name = "bookmark_flat_uuid",
//            joinColumns = @JoinColumn(name = "bookmark_uuid"),
//            uniqueConstraints = @UniqueConstraint(name = "user_flat", columnNames = {"user_uuid", "flat_uuid"}))
//    @Column(name = "flat_uuid")
    private UUID flatUuid;

    public Bookmark() {
    }

    public Bookmark(UUID userUuid, UUID flatUuid) {
        this.userUuid = userUuid;
        this.flatUuid = flatUuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public UUID getFlatUuid() {
        return flatUuid;
    }

    public void setFlatUuid(UUID flatUuid) {
        this.flatUuid = flatUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return Objects.equals(uuid, bookmark.uuid) && Objects.equals(userUuid, bookmark.userUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, userUuid);
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "uuid=" + uuid +
                ", userUuid=" + userUuid +
                '}';
    }
}
