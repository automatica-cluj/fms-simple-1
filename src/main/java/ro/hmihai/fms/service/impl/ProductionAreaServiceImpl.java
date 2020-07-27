package ro.hmihai.fms.service.impl;

import ro.hmihai.fms.service.ProductionAreaService;
import ro.hmihai.fms.domain.ProductionArea;
import ro.hmihai.fms.repository.ProductionAreaRepository;
import ro.hmihai.fms.service.dto.ProductionAreaDTO;
import ro.hmihai.fms.service.mapper.ProductionAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProductionArea}.
 */
@Service
@Transactional
public class ProductionAreaServiceImpl implements ProductionAreaService {

    private final Logger log = LoggerFactory.getLogger(ProductionAreaServiceImpl.class);

    private final ProductionAreaRepository productionAreaRepository;

    private final ProductionAreaMapper productionAreaMapper;

    public ProductionAreaServiceImpl(ProductionAreaRepository productionAreaRepository, ProductionAreaMapper productionAreaMapper) {
        this.productionAreaRepository = productionAreaRepository;
        this.productionAreaMapper = productionAreaMapper;
    }

    @Override
    public ProductionAreaDTO save(ProductionAreaDTO productionAreaDTO) {
        log.debug("Request to save ProductionArea : {}", productionAreaDTO);
        ProductionArea productionArea = productionAreaMapper.toEntity(productionAreaDTO);
        productionArea = productionAreaRepository.save(productionArea);
        return productionAreaMapper.toDto(productionArea);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductionAreaDTO> findAll() {
        log.debug("Request to get all ProductionAreas");
        return productionAreaRepository.findAll().stream()
            .map(productionAreaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProductionAreaDTO> findOne(Long id) {
        log.debug("Request to get ProductionArea : {}", id);
        return productionAreaRepository.findById(id)
            .map(productionAreaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductionArea : {}", id);
        productionAreaRepository.deleteById(id);
    }
}
