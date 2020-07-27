package ro.hmihai.fms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.hmihai.fms.web.rest.TestUtil;

public class OperatorDeviceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperatorDevice.class);
        OperatorDevice operatorDevice1 = new OperatorDevice();
        operatorDevice1.setId(1L);
        OperatorDevice operatorDevice2 = new OperatorDevice();
        operatorDevice2.setId(operatorDevice1.getId());
        assertThat(operatorDevice1).isEqualTo(operatorDevice2);
        operatorDevice2.setId(2L);
        assertThat(operatorDevice1).isNotEqualTo(operatorDevice2);
        operatorDevice1.setId(null);
        assertThat(operatorDevice1).isNotEqualTo(operatorDevice2);
    }
}
