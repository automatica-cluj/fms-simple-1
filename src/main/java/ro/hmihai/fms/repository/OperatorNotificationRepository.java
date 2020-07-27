package ro.hmihai.fms.repository;

import ro.hmihai.fms.domain.OperatorNotification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OperatorNotification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperatorNotificationRepository extends JpaRepository<OperatorNotification, Long> {
}
