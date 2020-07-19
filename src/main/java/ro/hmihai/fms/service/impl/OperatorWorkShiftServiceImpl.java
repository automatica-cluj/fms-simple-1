package ro.hmihai.fms.service.impl;

import ro.hmihai.fms.service.OperatorWorkShiftService;
import ro.hmihai.fms.domain.OperatorWorkShift;
import ro.hmihai.fms.repository.OperatorWorkShiftRepository;
import ro.hmihai.fms.service.dto.OperatorWorkShiftDTO;
import ro.hmihai.fms.service.mapper.OperatorWorkShiftMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OperatorWorkShift}.
 */
@Service
@Transactional
public class OperatorWorkShiftServiceImpl implements OperatorWorkShiftService {

    private final Logger log = LoggerFactory.getLogger(OperatorWorkShiftServiceImpl.class);

    private final OperatorWorkShiftRepository operatorWorkShiftRepository;

    private final OperatorWorkShiftMapper operatorWorkShiftMapper;

    public OperatorWorkShiftServiceImpl(OperatorWorkShiftRepository operatorWorkShiftRepository, OperatorWorkShiftMapper operatorWorkShiftMapper) {
        this.operatorWorkShiftRepository = operatorWorkShiftRepository;
        this.operatorWorkShiftMapper = operatorWorkShiftMapper;
    }

    @Override
    public OperatorWorkShiftDTO save(OperatorWorkShiftDTO operatorWorkShiftDTO) {
        log.debug("Request to save OperatorWorkShift : {}", operatorWorkShiftDTO);
        OperatorWorkShift operatorWorkShift = operatorWorkShiftMapper.toEntity(operatorWorkShiftDTO);
        operatorWorkShift = operatorWorkShiftRepository.save(operatorWorkShift);
        return operatorWorkShiftMapper.toDto(operatorWorkShift);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OperatorWorkShiftDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OperatorWorkShifts");
        return operatorWorkShiftRepository.findAll(pageable)
            .map(operatorWorkShiftMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OperatorWorkShiftDTO> findOne(Long id) {
        log.debug("Request to get OperatorWorkShift : {}", id);
        return operatorWorkShiftRepository.findById(id)
            .map(operatorWorkShiftMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OperatorWorkShift : {}", id);
        operatorWorkShiftRepository.deleteById(id);
    }
}
