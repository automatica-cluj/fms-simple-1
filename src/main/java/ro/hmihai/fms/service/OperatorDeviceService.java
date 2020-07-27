package ro.hmihai.fms.service;

import ro.hmihai.fms.service.dto.OperatorDeviceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ro.hmihai.fms.domain.OperatorDevice}.
 */
public interface OperatorDeviceService {

    /**
     * Save a operatorDevice.
     *
     * @param operatorDeviceDTO the entity to save.
     * @return the persisted entity.
     */
    OperatorDeviceDTO save(OperatorDeviceDTO operatorDeviceDTO);

    /**
     * Get all the operatorDevices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OperatorDeviceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" operatorDevice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OperatorDeviceDTO> findOne(Long id);

    /**
     * Delete the "id" operatorDevice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
