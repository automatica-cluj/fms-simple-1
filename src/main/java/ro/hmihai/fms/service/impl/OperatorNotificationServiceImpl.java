package ro.hmihai.fms.service.impl;

import ro.hmihai.fms.service.OperatorNotificationService;
import ro.hmihai.fms.domain.OperatorNotification;
import ro.hmihai.fms.repository.OperatorNotificationRepository;
import ro.hmihai.fms.service.dto.OperatorNotificationDTO;
import ro.hmihai.fms.service.mapper.OperatorNotificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OperatorNotification}.
 */
@Service
@Transactional
public class OperatorNotificationServiceImpl implements OperatorNotificationService {

    private final Logger log = LoggerFactory.getLogger(OperatorNotificationServiceImpl.class);

    private final OperatorNotificationRepository operatorNotificationRepository;

    private final OperatorNotificationMapper operatorNotificationMapper;

    public OperatorNotificationServiceImpl(OperatorNotificationRepository operatorNotificationRepository, OperatorNotificationMapper operatorNotificationMapper) {
        this.operatorNotificationRepository = operatorNotificationRepository;
        this.operatorNotificationMapper = operatorNotificationMapper;
    }

    @Override
    public OperatorNotificationDTO save(OperatorNotificationDTO operatorNotificationDTO) {
        log.debug("Request to save OperatorNotification : {}", operatorNotificationDTO);
        OperatorNotification operatorNotification = operatorNotificationMapper.toEntity(operatorNotificationDTO);
        operatorNotification = operatorNotificationRepository.save(operatorNotification);
        return operatorNotificationMapper.toDto(operatorNotification);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OperatorNotificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OperatorNotifications");
        return operatorNotificationRepository.findAll(pageable)
            .map(operatorNotificationMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OperatorNotificationDTO> findOne(Long id) {
        log.debug("Request to get OperatorNotification : {}", id);
        return operatorNotificationRepository.findById(id)
            .map(operatorNotificationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OperatorNotification : {}", id);
        operatorNotificationRepository.deleteById(id);
    }
}
