package ro.hmihai.fms.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OperatorNotificationMapperTest {

    private OperatorNotificationMapper operatorNotificationMapper;

    @BeforeEach
    public void setUp() {
        operatorNotificationMapper = new OperatorNotificationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(operatorNotificationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(operatorNotificationMapper.fromId(null)).isNull();
    }
}
