package ro.hmihai.fms.repository;

import ro.hmihai.fms.domain.FactoryEvent;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FactoryEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactoryEventRepository extends JpaRepository<FactoryEvent, Long> {
}
