package ada.tech.domain.repository;

import ada.tech.domain.entity.ColorEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface ColorRepository extends PanacheRepository<ColorEntity> {
}
