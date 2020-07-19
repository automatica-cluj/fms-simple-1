package ro.hmihai.fms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OperatorWorkShiftMapperTest {

    private OperatorWorkShiftMapper operatorWorkShiftMapper;

    @BeforeEach
    public void setUp() {
        operatorWorkShiftMapper = new OperatorWorkShiftMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(operatorWorkShiftMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(operatorWorkShiftMapper.fromId(null)).isNull();
    }
}
