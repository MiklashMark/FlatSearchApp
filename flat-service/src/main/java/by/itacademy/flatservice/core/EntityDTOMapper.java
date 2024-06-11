package by.itacademy.flatservice.core;

import by.itacademy.exceptions.dto.flat.FlatDTO;
import by.itacademy.exceptions.dto.flat.bookmark.BookmarkDTO;
import by.itacademy.flatservice.repository.entity.Bookmark;
import by.itacademy.flatservice.repository.entity.flat.Flat;
import by.itacademy.flatservice.repository.entity.flat.Photo;
import by.itacademy.flatservice.repository.subscribe.Subscribe;
import by.itacademy.flatservice.repository.subscribe.SubscribeFilter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EntityDTOMapper {

    EntityDTOMapper INSTANCE = Mappers.getMapper(EntityDTOMapper.class);

    @Mapping(target = "photos", ignore = true)
    default Flat flatDTOToFlat(FlatDTO flatDTO) {
        if (flatDTO == null) {
            return null;
        }
        Flat flat = new Flat();
        flat.setOriginalUrl(flatDTO.getOriginalUrl());
        flat.setUuid(UUID.fromString(String.valueOf(flatDTO.getUuid())));
        flat.setCreateDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(flatDTO.getDtCreate()), ZoneId.systemDefault()));
        flat.setUpdateDate(LocalDateTime.ofInstant(Instant.ofEpochMilli(flatDTO.getDtUpdate()), ZoneId.systemDefault()));
        flat.setOfferType(flatDTO.getOfferType());
        flat.setDescription(flatDTO.getDescription());
        flat.setFloor(flatDTO.getFloor());
        flat.setBedrooms(flatDTO.getBedrooms());
        flat.setPrice(flatDTO.getPrice());
        flat.setArea(flatDTO.getArea());


        return flat;
    }

    default FlatDTO flatToFlatDTO(Flat flat) {
        if (flat == null) {
            return null;
        }
        FlatDTO flatDTO = new FlatDTO();
        flatDTO.setOriginalUrl(flat.getOriginalUrl());
        flatDTO.setUuid(UUID.fromString(flat.getUuid().toString()));
        flatDTO.setDtCreate(flat.getCreateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        flatDTO.setDtUpdate(flat.getUpdateDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        flatDTO.setOfferType(flat.getOfferType());
        flatDTO.setDescription(flat.getDescription());
        flatDTO.setFloor(flat.getFloor());
        flatDTO.setBedrooms(flat.getBedrooms());
        flatDTO.setPrice(flat.getPrice());
        flatDTO.setArea(flat.getArea());

        // Маппинг фотографий
        if (flat.getPhotos() != null) {
            List<String> photoUrls = flat.getPhotos().stream()
                    .map(Photo::getPhotoUrl)
                    .collect(Collectors.toList());
            flatDTO.setPhotoUrls(photoUrls);
        }
        return flatDTO;
    }

    default List<FlatDTO> flatListToFlatDTOList(List<Flat> content) {
        return content.stream()
                .map(this::flatToFlatDTO)
                .collect(Collectors.toList());
    }

    default BookmarkDTO createBookmarkDTO(Page<Bookmark> bookmarkPage, List<FlatDTO> flatDTOS) {
        BookmarkDTO bookmarkDTO = new BookmarkDTO();
        bookmarkDTO.setNumber(bookmarkPage.getNumber());
        bookmarkDTO.setSize(bookmarkPage.getSize());
        bookmarkDTO.setTotalPages(bookmarkPage.getTotalPages());
        bookmarkDTO.setTotalElements(bookmarkPage.getTotalElements());
        bookmarkDTO.setNumberOfElements(bookmarkPage.getNumberOfElements());
        bookmarkDTO.setFirst(bookmarkPage.isFirst());
        bookmarkDTO.setLast(bookmarkPage.isLast());
        bookmarkDTO.setContent(flatDTOS);
        return bookmarkDTO;
    }

    SubscribeFilter toSubscribeFilter(SubscribeFilterDTO subscribeFilterDTO);

    FlatFilter toFlatFilter(SubscribeFilter subscribeFilter);

    default SubscribeDTO toSubscribeDTO(Subscribe subscribe) {
        if (subscribe == null) {
            return null;
        }
        return new SubscribeDTO()
                .setUuid(subscribe.getUuid())
                .setUserUuid(subscribe.getUserUuid())
                .setDtCreate(subscribe.getDtCreate().toEpochSecond(ZoneOffset.UTC) * 1000)
                .setDtUpdate(subscribe.getDtUpdate().toEpochSecond(ZoneOffset.UTC) * 1000)
                .setSubscribeFilters(EntityDTOMapper.INSTANCE.toSubscribeFilterDTOList(subscribe.getSubscribeFilters()));
    }

    SubscribeFilterDTO toSubscribeFilterDTO(SubscribeFilter subscribeFilter);

    default List<SubscribeFilterDTO> toSubscribeFilterDTOList(List<SubscribeFilter> subscribeFilters) {
        return subscribeFilters.stream()
                .map(this::toSubscribeFilterDTO)
                .collect(Collectors.toList());
    }

}
