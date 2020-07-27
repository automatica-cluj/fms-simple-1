package ro.hmihai.fms.service.mapper;


import ro.hmihai.fms.domain.*;
import ro.hmihai.fms.service.dto.OperatorDeviceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OperatorDevice} and its DTO {@link OperatorDeviceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OperatorDeviceMapper extends EntityMapper<OperatorDeviceDTO, OperatorDevice> {



    default OperatorDevice fromId(Long id) {
        if (id == null) {
            return null;
        }
        OperatorDevice operatorDevice = new OperatorDevice();
        operatorDevice.setId(id);
        return operatorDevice;
    }
}
