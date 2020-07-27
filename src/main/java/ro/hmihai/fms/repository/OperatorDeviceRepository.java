package ro.hmihai.fms.repository;

import ro.hmihai.fms.domain.OperatorDevice;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OperatorDevice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperatorDeviceRepository extends JpaRepository<OperatorDevice, Long> {
}
