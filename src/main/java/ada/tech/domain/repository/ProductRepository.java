package ada.tech.domain.repository;

import ada.tech.domain.entity.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface ProductRepository extends PanacheRepository<ProductEntity> {
}
