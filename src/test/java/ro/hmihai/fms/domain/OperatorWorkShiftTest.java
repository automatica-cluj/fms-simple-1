package ro.hmihai.fms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.hmihai.fms.web.rest.TestUtil;

public class OperatorWorkShiftTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperatorWorkShift.class);
        OperatorWorkShift operatorWorkShift1 = new OperatorWorkShift();
        operatorWorkShift1.setId(1L);
        OperatorWorkShift operatorWorkShift2 = new OperatorWorkShift();
        operatorWorkShift2.setId(operatorWorkShift1.getId());
        assertThat(operatorWorkShift1).isEqualTo(operatorWorkShift2);
        operatorWorkShift2.setId(2L);
        assertThat(operatorWorkShift1).isNotEqualTo(operatorWorkShift2);
        operatorWorkShift1.setId(null);
        assertThat(operatorWorkShift1).isNotEqualTo(operatorWorkShift2);
    }
}
