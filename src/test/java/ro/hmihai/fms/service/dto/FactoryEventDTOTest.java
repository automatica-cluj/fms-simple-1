package ro.hmihai.fms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.hmihai.fms.web.rest.TestUtil;

public class FactoryEventDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FactoryEventDTO.class);
        FactoryEventDTO factoryEventDTO1 = new FactoryEventDTO();
        factoryEventDTO1.setId(1L);
        FactoryEventDTO factoryEventDTO2 = new FactoryEventDTO();
        assertThat(factoryEventDTO1).isNotEqualTo(factoryEventDTO2);
        factoryEventDTO2.setId(factoryEventDTO1.getId());
        assertThat(factoryEventDTO1).isEqualTo(factoryEventDTO2);
        factoryEventDTO2.setId(2L);
        assertThat(factoryEventDTO1).isNotEqualTo(factoryEventDTO2);
        factoryEventDTO1.setId(null);
        assertThat(factoryEventDTO1).isNotEqualTo(factoryEventDTO2);
    }
}
