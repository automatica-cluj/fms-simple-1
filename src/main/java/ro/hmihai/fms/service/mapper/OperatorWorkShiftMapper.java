package ro.hmihai.fms.service.mapper;


import ro.hmihai.fms.domain.*;
import ro.hmihai.fms.service.dto.OperatorWorkShiftDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OperatorWorkShift} and its DTO {@link OperatorWorkShiftDTO}.
 */
@Mapper(componentModel = "spring", uses = {OperatorDeviceMapper.class, OperatorMapper.class})
public interface OperatorWorkShiftMapper extends EntityMapper<OperatorWorkShiftDTO, OperatorWorkShift> {

    @Mapping(source = "device.id", target = "deviceId")
    @Mapping(source = "operator.id", target = "operatorId")
    OperatorWorkShiftDTO toDto(OperatorWorkShift operatorWorkShift);

    @Mapping(source = "deviceId", target = "device")
    @Mapping(source = "operatorId", target = "operator")
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "removeNotification", ignore = true)
    @Mapping(target = "productionAreas", ignore = true)
    @Mapping(target = "removeProductionArea", ignore = true)
    OperatorWorkShift toEntity(OperatorWorkShiftDTO operatorWorkShiftDTO);

    default OperatorWorkShift fromId(Long id) {
        if (id == null) {
            return null;
        }
        OperatorWorkShift operatorWorkShift = new OperatorWorkShift();
        operatorWorkShift.setId(id);
        return operatorWorkShift;
    }
}
