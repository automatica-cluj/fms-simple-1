package ro.hmihai.fms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.hmihai.fms.web.rest.TestUtil;

public class OperatorNotificationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperatorNotificationDTO.class);
        OperatorNotificationDTO operatorNotificationDTO1 = new OperatorNotificationDTO();
        operatorNotificationDTO1.setId(1L);
        OperatorNotificationDTO operatorNotificationDTO2 = new OperatorNotificationDTO();
        assertThat(operatorNotificationDTO1).isNotEqualTo(operatorNotificationDTO2);
        operatorNotificationDTO2.setId(operatorNotificationDTO1.getId());
        assertThat(operatorNotificationDTO1).isEqualTo(operatorNotificationDTO2);
        operatorNotificationDTO2.setId(2L);
        assertThat(operatorNotificationDTO1).isNotEqualTo(operatorNotificationDTO2);
        operatorNotificationDTO1.setId(null);
        assertThat(operatorNotificationDTO1).isNotEqualTo(operatorNotificationDTO2);
    }
}
