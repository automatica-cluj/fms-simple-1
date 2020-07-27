package ro.hmihai.fms.service;

import ro.hmihai.fms.service.dto.ProductionAreaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ro.hmihai.fms.domain.ProductionArea}.
 */
public interface ProductionAreaService {

    /**
     * Save a productionArea.
     *
     * @param productionAreaDTO the entity to save.
     * @return the persisted entity.
     */
    ProductionAreaDTO save(ProductionAreaDTO productionAreaDTO);

    /**
     * Get all the productionAreas.
     *
     * @return the list of entities.
     */
    List<ProductionAreaDTO> findAll();


    /**
     * Get the "id" productionArea.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductionAreaDTO> findOne(Long id);

    /**
     * Delete the "id" productionArea.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
