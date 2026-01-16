package ada.tech.domain.repository;

import ada.tech.domain.entity.ColorEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;

public interface ColorRepository extends PanacheRepository<ColorEntity> {
    Optional findByName(String name);
}
