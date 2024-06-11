package by.itacademy.flatservice.repository.subscribe;

import by.itacademy.exceptions.dto.flat.OfferType;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "flat", name = "subscribe_filter")
public class SubscribeFilter {
    @Id
    @GeneratedValue
    private UUID uuid;
    @Enumerated(EnumType.STRING)
    @Column(name = "offer_type")
    private OfferType offerType;
    @Column(name = "price_from")
    private int priceFrom;
    @Column(name = "price_to")
    private int priceTo;
    @Column(name = "bedrooms_from")
    private int bedroomsFrom;
    @Column(name = "bedrooms_to")
    private int bedroomsTo;
    @Column(name = "area_from")
    private int areaFrom;
    @Column(name = "area_to")
    private int areaTo;
    @Column(name = "floors")
    @ElementCollection
    private List<Integer> floors;
    private boolean photo;

    public SubscribeFilter() {
    }

    public SubscribeFilter(UUID uuid, OfferType offerType,
                           int priceFrom, int priceTo, int bedroomsFrom, int bedroomsTo,
                           int areaFrom, int areaTo, List<Integer> floors, boolean photo) {
        this.uuid = uuid;
        this.offerType = offerType;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.bedroomsFrom = bedroomsFrom;
        this.bedroomsTo = bedroomsTo;
        this.areaFrom = areaFrom;
        this.areaTo = areaTo;
        this.floors = floors;
        this.photo = photo;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offer_type) {
        this.offerType = offer_type;
    }

    public int getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(int priceFrom) {
        this.priceFrom = priceFrom;
    }

    public int getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(int priceTo) {
        this.priceTo = priceTo;
    }

    public int getBedroomsFrom() {
        return bedroomsFrom;
    }

    public void setBedroomsFrom(int bedroomsFrom) {
        this.bedroomsFrom = bedroomsFrom;
    }

    public int getBedroomsTo() {
        return bedroomsTo;
    }

    public void setBedroomsTo(int bedroomsTo) {
        this.bedroomsTo = bedroomsTo;
    }

    public int getAreaFrom() {
        return areaFrom;
    }

    public void setAreaFrom(int areaFrom) {
        this.areaFrom = areaFrom;
    }

    public int getAreaTo() {
        return areaTo;
    }

    public void setAreaTo(int areaTo) {
        this.areaTo = areaTo;
    }

    public List<Integer> getFloors() {
        return floors;
    }

    public void setFloors(List<Integer> floors) {
        this.floors = floors;
    }

    public boolean isPhoto() {
        return photo;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscribeFilter that = (SubscribeFilter) o;
        boolean isFloorsEqual;
        if (floors == null && that.floors == null) {
            isFloorsEqual = true;
        } else if (floors != null && that.floors != null) {
            isFloorsEqual = floors.size() == that.floors.size() && floors.containsAll(that.floors);
        } else {
            isFloorsEqual = false;
        }
        return priceFrom == that.priceFrom && priceTo == that.priceTo && bedroomsFrom == that.bedroomsFrom && bedroomsTo == that.bedroomsTo
                && areaFrom == that.areaFrom && areaTo == that.areaTo && photo == that.photo
                && offerType == that.offerType && isFloorsEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceFrom, priceTo, bedroomsFrom, bedroomsTo,
                areaFrom, areaTo, photo, offerType, floors);
    }

    @Override
    public String toString() {
        return "SubscribeFilter{" +
                "uuid=" + uuid +
                ", offerType=" + offerType +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", bedroomsFrom=" + bedroomsFrom +
                ", bedroomsTo=" + bedroomsTo +
                ", areaFrom=" + areaFrom +
                ", areaTo=" + areaTo +
                ", floors=" + floors +
                ", photo=" + photo +
                '}';
    }

}
