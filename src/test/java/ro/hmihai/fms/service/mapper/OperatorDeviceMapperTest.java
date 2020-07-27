package ro.hmihai.fms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OperatorDeviceMapperTest {

    private OperatorDeviceMapper operatorDeviceMapper;

    @BeforeEach
    public void setUp() {
        operatorDeviceMapper = new OperatorDeviceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(operatorDeviceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(operatorDeviceMapper.fromId(null)).isNull();
    }
}
