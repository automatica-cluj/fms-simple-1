package ro.hmihai.fms.service.mapper;


import ro.hmihai.fms.domain.*;
import ro.hmihai.fms.service.dto.FactoryEventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FactoryEvent} and its DTO {@link FactoryEventDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductionAreaMapper.class})
public interface FactoryEventMapper extends EntityMapper<FactoryEventDTO, FactoryEvent> {

    @Mapping(source = "productionArea.id", target = "productionAreaId")
    FactoryEventDTO toDto(FactoryEvent factoryEvent);

    @Mapping(source = "productionAreaId", target = "productionArea")
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "removeNotification", ignore = true)
    FactoryEvent toEntity(FactoryEventDTO factoryEventDTO);

    default FactoryEvent fromId(Long id) {
        if (id == null) {
            return null;
        }
        FactoryEvent factoryEvent = new FactoryEvent();
        factoryEvent.setId(id);
        return factoryEvent;
    }
}
