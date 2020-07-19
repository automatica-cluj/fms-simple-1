package ro.hmihai.fms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.hmihai.fms.web.rest.TestUtil;

public class OperatorWorkShiftDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperatorWorkShiftDTO.class);
        OperatorWorkShiftDTO operatorWorkShiftDTO1 = new OperatorWorkShiftDTO();
        operatorWorkShiftDTO1.setId(1L);
        OperatorWorkShiftDTO operatorWorkShiftDTO2 = new OperatorWorkShiftDTO();
        assertThat(operatorWorkShiftDTO1).isNotEqualTo(operatorWorkShiftDTO2);
        operatorWorkShiftDTO2.setId(operatorWorkShiftDTO1.getId());
        assertThat(operatorWorkShiftDTO1).isEqualTo(operatorWorkShiftDTO2);
        operatorWorkShiftDTO2.setId(2L);
        assertThat(operatorWorkShiftDTO1).isNotEqualTo(operatorWorkShiftDTO2);
        operatorWorkShiftDTO1.setId(null);
        assertThat(operatorWorkShiftDTO1).isNotEqualTo(operatorWorkShiftDTO2);
    }
}
