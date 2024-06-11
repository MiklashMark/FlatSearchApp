package by.itacademy.parserservice.repository.api;

import by.itacademy.parserservice.repository.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFlatRepository extends JpaRepository<Flat, UUID> {
    boolean existsByOriginalUrl(String url);
}
