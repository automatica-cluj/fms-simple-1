package ro.hmihai.fms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.hmihai.fms.web.rest.TestUtil;

public class OperatorNotificationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperatorNotification.class);
        OperatorNotification operatorNotification1 = new OperatorNotification();
        operatorNotification1.setId(1L);
        OperatorNotification operatorNotification2 = new OperatorNotification();
        operatorNotification2.setId(operatorNotification1.getId());
        assertThat(operatorNotification1).isEqualTo(operatorNotification2);
        operatorNotification2.setId(2L);
        assertThat(operatorNotification1).isNotEqualTo(operatorNotification2);
        operatorNotification1.setId(null);
        assertThat(operatorNotification1).isNotEqualTo(operatorNotification2);
    }
}
