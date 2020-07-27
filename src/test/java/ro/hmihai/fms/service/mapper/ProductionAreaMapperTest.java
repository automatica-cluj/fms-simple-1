package ro.hmihai.fms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductionAreaMapperTest {

    private ProductionAreaMapper productionAreaMapper;

    @BeforeEach
    public void setUp() {
        productionAreaMapper = new ProductionAreaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(productionAreaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(productionAreaMapper.fromId(null)).isNull();
    }
}
