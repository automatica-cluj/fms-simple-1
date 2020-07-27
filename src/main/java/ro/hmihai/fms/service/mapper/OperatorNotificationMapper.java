package ro.hmihai.fms.service.mapper;


import ro.hmihai.fms.domain.*;
import ro.hmihai.fms.service.dto.OperatorNotificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OperatorNotification} and its DTO {@link OperatorNotificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {OperatorWorkShiftMapper.class, FactoryEventMapper.class})
public interface OperatorNotificationMapper extends EntityMapper<OperatorNotificationDTO, OperatorNotification> {

    @Mapping(source = "operatorWorkShift.id", target = "operatorWorkShiftId")
    @Mapping(source = "factoryEvent.id", target = "factoryEventId")
    OperatorNotificationDTO toDto(OperatorNotification operatorNotification);

    @Mapping(source = "operatorWorkShiftId", target = "operatorWorkShift")
    @Mapping(source = "factoryEventId", target = "factoryEvent")
    OperatorNotification toEntity(OperatorNotificationDTO operatorNotificationDTO);

    default OperatorNotification fromId(Long id) {
        if (id == null) {
            return null;
        }
        OperatorNotification operatorNotification = new OperatorNotification();
        operatorNotification.setId(id);
        return operatorNotification;
    }
}
