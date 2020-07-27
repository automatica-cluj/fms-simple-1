package ro.hmihai.fms.service.mapper;


import ro.hmihai.fms.domain.*;
import ro.hmihai.fms.service.dto.ProductionAreaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductionArea} and its DTO {@link ProductionAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {OperatorWorkShiftMapper.class})
public interface ProductionAreaMapper extends EntityMapper<ProductionAreaDTO, ProductionArea> {

    @Mapping(source = "operatorWorkShift.id", target = "operatorWorkShiftId")
    ProductionAreaDTO toDto(ProductionArea productionArea);

    @Mapping(source = "operatorWorkShiftId", target = "operatorWorkShift")
    ProductionArea toEntity(ProductionAreaDTO productionAreaDTO);

    default ProductionArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductionArea productionArea = new ProductionArea();
        productionArea.setId(id);
        return productionArea;
    }
}
