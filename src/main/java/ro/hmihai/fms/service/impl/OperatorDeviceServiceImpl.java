package ro.hmihai.fms.service.impl;

import ro.hmihai.fms.service.OperatorDeviceService;
import ro.hmihai.fms.domain.OperatorDevice;
import ro.hmihai.fms.repository.OperatorDeviceRepository;
import ro.hmihai.fms.service.dto.OperatorDeviceDTO;
import ro.hmihai.fms.service.mapper.OperatorDeviceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OperatorDevice}.
 */
@Service
@Transactional
public class OperatorDeviceServiceImpl implements OperatorDeviceService {

    private final Logger log = LoggerFactory.getLogger(OperatorDeviceServiceImpl.class);

    private final OperatorDeviceRepository operatorDeviceRepository;

    private final OperatorDeviceMapper operatorDeviceMapper;

    public OperatorDeviceServiceImpl(OperatorDeviceRepository operatorDeviceRepository, OperatorDeviceMapper operatorDeviceMapper) {
        this.operatorDeviceRepository = operatorDeviceRepository;
        this.operatorDeviceMapper = operatorDeviceMapper;
    }

    @Override
    public OperatorDeviceDTO save(OperatorDeviceDTO operatorDeviceDTO) {
        log.debug("Request to save OperatorDevice : {}", operatorDeviceDTO);
        OperatorDevice operatorDevice = operatorDeviceMapper.toEntity(operatorDeviceDTO);
        operatorDevice = operatorDeviceRepository.save(operatorDevice);
        return operatorDeviceMapper.toDto(operatorDevice);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OperatorDeviceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OperatorDevices");
        return operatorDeviceRepository.findAll(pageable)
            .map(operatorDeviceMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OperatorDeviceDTO> findOne(Long id) {
        log.debug("Request to get OperatorDevice : {}", id);
        return operatorDeviceRepository.findById(id)
            .map(operatorDeviceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OperatorDevice : {}", id);
        operatorDeviceRepository.deleteById(id);
    }
}
