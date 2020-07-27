package ro.hmihai.fms.service;

import ro.hmihai.fms.service.dto.OperatorNotificationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ro.hmihai.fms.domain.OperatorNotification}.
 */
public interface OperatorNotificationService {

    /**
     * Save a operatorNotification.
     *
     * @param operatorNotificationDTO the entity to save.
     * @return the persisted entity.
     */
    OperatorNotificationDTO save(OperatorNotificationDTO operatorNotificationDTO);

    /**
     * Get all the operatorNotifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OperatorNotificationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" operatorNotification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OperatorNotificationDTO> findOne(Long id);

    /**
     * Delete the "id" operatorNotification.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
