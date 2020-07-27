package ro.hmihai.fms.service;

import ro.hmihai.fms.service.dto.FactoryEventDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ro.hmihai.fms.domain.FactoryEvent}.
 */
public interface FactoryEventService {

    /**
     * Save a factoryEvent.
     *
     * @param factoryEventDTO the entity to save.
     * @return the persisted entity.
     */
    FactoryEventDTO save(FactoryEventDTO factoryEventDTO);

    /**
     * Get all the factoryEvents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FactoryEventDTO> findAll(Pageable pageable);


    /**
     * Get the "id" factoryEvent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FactoryEventDTO> findOne(Long id);

    /**
     * Delete the "id" factoryEvent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
