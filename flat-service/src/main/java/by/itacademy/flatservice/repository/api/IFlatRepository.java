package by.itacademy.flatservice.repository.api;

import by.itacademy.flatservice.repository.entity.Flat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IFlatRepository extends JpaRepository<Flat, String> {
    Page<Flat> findAll(Specification<Flat> filter, Pageable pageable);

}
