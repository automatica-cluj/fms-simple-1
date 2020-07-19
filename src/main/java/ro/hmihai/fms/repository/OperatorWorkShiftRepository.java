package ro.hmihai.fms.repository;

import ro.hmihai.fms.domain.OperatorWorkShift;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OperatorWorkShift entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperatorWorkShiftRepository extends JpaRepository<OperatorWorkShift, Long> {
}
