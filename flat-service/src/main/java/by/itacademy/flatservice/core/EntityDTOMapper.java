package by.itacademy.flatservice.core;

import by.itacademy.exceptions.dto.flat.FlatDTO;
import by.itacademy.exceptions.dto.flat.FlatPageDTO;
import by.itacademy.flatservice.repository.entity.Flat;
import by.itacademy.flatservice.repository.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.web.server.MimeMappings;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
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
}
