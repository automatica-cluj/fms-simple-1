package ro.hmihai.fms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FactoryEventMapperTest {

    private FactoryEventMapper factoryEventMapper;

    @BeforeEach
    public void setUp() {
        factoryEventMapper = new FactoryEventMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(factoryEventMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(factoryEventMapper.fromId(null)).isNull();
    }
}
