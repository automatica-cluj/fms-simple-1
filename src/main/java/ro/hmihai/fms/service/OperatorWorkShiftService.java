package ro.hmihai.fms.service;

import ro.hmihai.fms.service.dto.OperatorWorkShiftDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ro.hmihai.fms.domain.OperatorWorkShift}.
 */
public interface OperatorWorkShiftService {

    /**
     * Save a operatorWorkShift.
     *
     * @param operatorWorkShiftDTO the entity to save.
     * @return the persisted entity.
     */
    OperatorWorkShiftDTO save(OperatorWorkShiftDTO operatorWorkShiftDTO);

    /**
     * Get all the operatorWorkShifts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OperatorWorkShiftDTO> findAll(Pageable pageable);


    /**
     * Get the "id" operatorWorkShift.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OperatorWorkShiftDTO> findOne(Long id);

    /**
     * Delete the "id" operatorWorkShift.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
