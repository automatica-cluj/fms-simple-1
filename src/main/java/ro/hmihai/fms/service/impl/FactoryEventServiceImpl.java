package ro.hmihai.fms.service.impl;

import ro.hmihai.fms.service.FactoryEventService;
import ro.hmihai.fms.domain.FactoryEvent;
import ro.hmihai.fms.repository.FactoryEventRepository;
import ro.hmihai.fms.service.dto.FactoryEventDTO;
import ro.hmihai.fms.service.mapper.FactoryEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FactoryEvent}.
 */
@Service
@Transactional
public class FactoryEventServiceImpl implements FactoryEventService {

    private final Logger log = LoggerFactory.getLogger(FactoryEventServiceImpl.class);

    private final FactoryEventRepository factoryEventRepository;

    private final FactoryEventMapper factoryEventMapper;

    public FactoryEventServiceImpl(FactoryEventRepository factoryEventRepository, FactoryEventMapper factoryEventMapper) {
        this.factoryEventRepository = factoryEventRepository;
        this.factoryEventMapper = factoryEventMapper;
    }

    @Override
    public FactoryEventDTO save(FactoryEventDTO factoryEventDTO) {
        log.debug("Request to save FactoryEvent : {}", factoryEventDTO);
        FactoryEvent factoryEvent = factoryEventMapper.toEntity(factoryEventDTO);
        factoryEvent = factoryEventRepository.save(factoryEvent);
        return factoryEventMapper.toDto(factoryEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FactoryEventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FactoryEvents");
        return factoryEventRepository.findAll(pageable)
            .map(factoryEventMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FactoryEventDTO> findOne(Long id) {
        log.debug("Request to get FactoryEvent : {}", id);
        return factoryEventRepository.findById(id)
            .map(factoryEventMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FactoryEvent : {}", id);
        factoryEventRepository.deleteById(id);
    }
}
