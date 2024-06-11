package by.itacademy.flatservice.repository.api;

import by.itacademy.flatservice.repository.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ISubscribeRepository extends JpaRepository<Subscribe, UUID> {
    Optional<Subscribe> findSubscribeByUserUuid(UUID userUuid);
}
