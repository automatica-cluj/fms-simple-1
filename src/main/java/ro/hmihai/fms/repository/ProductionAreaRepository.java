package ro.hmihai.fms.repository;

import ro.hmihai.fms.domain.ProductionArea;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProductionArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductionAreaRepository extends JpaRepository<ProductionArea, Long> {
}
