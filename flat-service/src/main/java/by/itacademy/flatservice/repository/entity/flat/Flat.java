package by.itacademy.flatservice.repository.entity.flat;

import by.itacademy.exceptions.dto.flat.OfferType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "flat", name = "flat")
public class Flat {
    @Id
    @GeneratedValue
    private UUID uuid;
    @Column(name = "original_url")
    private String originalUrl;
    @Column(name = "dt_create")
    private LocalDateTime createDate;
    @Column(name = "dt_update")
    @Version
    private LocalDateTime updateDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "offer_type")
    private OfferType offerType;
    private String description;
    private int floor;
    private int bedrooms;
    private int price;
    private int area;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "flat_uuid")
    private Set<Photo> photos;

    public Flat() {
    }

    public Flat(String originalUrl, LocalDateTime createDate, LocalDateTime updateDate, OfferType offerType, String description, int floor, int bedrooms, int price, int area, Set<Photo> photos) {
        this.originalUrl = originalUrl;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.offerType = offerType;
        this.description = description;
        this.floor = floor;
        this.bedrooms = bedrooms;
        this.price = price;
        this.area = area;
        this.photos = photos;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return floor == flat.floor && bedrooms == flat.bedrooms && price == flat.price && area == flat.area && Objects.equals(originalUrl, flat.originalUrl) && Objects.equals(uuid, flat.uuid) && Objects.equals(createDate, flat.createDate) && Objects.equals(updateDate, flat.updateDate) && offerType == flat.offerType && Objects.equals(description, flat.description) && Objects.equals(photos, flat.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalUrl, uuid, createDate, updateDate, offerType, description, floor, bedrooms, price, area, photos);
    }
}