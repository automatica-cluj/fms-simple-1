package ro.hmihai.fms.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.hmihai.fms.web.rest.TestUtil;

public class ProductionAreaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductionAreaDTO.class);
        ProductionAreaDTO productionAreaDTO1 = new ProductionAreaDTO();
        productionAreaDTO1.setId(1L);
        ProductionAreaDTO productionAreaDTO2 = new ProductionAreaDTO();
        assertThat(productionAreaDTO1).isNotEqualTo(productionAreaDTO2);
        productionAreaDTO2.setId(productionAreaDTO1.getId());
        assertThat(productionAreaDTO1).isEqualTo(productionAreaDTO2);
        productionAreaDTO2.setId(2L);
        assertThat(productionAreaDTO1).isNotEqualTo(productionAreaDTO2);
        productionAreaDTO1.setId(null);
        assertThat(productionAreaDTO1).isNotEqualTo(productionAreaDTO2);
    }
}
