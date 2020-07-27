package ro.hmihai.fms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.hmihai.fms.web.rest.TestUtil;

public class OperatorDeviceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperatorDeviceDTO.class);
        OperatorDeviceDTO operatorDeviceDTO1 = new OperatorDeviceDTO();
        operatorDeviceDTO1.setId(1L);
        OperatorDeviceDTO operatorDeviceDTO2 = new OperatorDeviceDTO();
        assertThat(operatorDeviceDTO1).isNotEqualTo(operatorDeviceDTO2);
        operatorDeviceDTO2.setId(operatorDeviceDTO1.getId());
        assertThat(operatorDeviceDTO1).isEqualTo(operatorDeviceDTO2);
        operatorDeviceDTO2.setId(2L);
        assertThat(operatorDeviceDTO1).isNotEqualTo(operatorDeviceDTO2);
        operatorDeviceDTO1.setId(null);
        assertThat(operatorDeviceDTO1).isNotEqualTo(operatorDeviceDTO2);
    }
}
