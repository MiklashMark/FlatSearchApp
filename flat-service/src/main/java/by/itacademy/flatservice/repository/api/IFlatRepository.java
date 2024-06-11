package by.itacademy.flatservice.repository.api;

import by.itacademy.flatservice.repository.entity.flat.Flat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface IFlatRepository extends JpaRepository<Flat, UUID>, JpaSpecificationExecutor<Flat> {
    Page<Flat> findAll(Specification<Flat> filter, Pageable pageable);
    Optional<Flat> findByUuid(UUID uuid);

}
