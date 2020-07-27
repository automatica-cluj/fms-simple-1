package ro.hmihai.fms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.hmihai.fms.web.rest.TestUtil;

public class ProductionAreaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductionArea.class);
        ProductionArea productionArea1 = new ProductionArea();
        productionArea1.setId(1L);
        ProductionArea productionArea2 = new ProductionArea();
        productionArea2.setId(productionArea1.getId());
        assertThat(productionArea1).isEqualTo(productionArea2);
        productionArea2.setId(2L);
        assertThat(productionArea1).isNotEqualTo(productionArea2);
        productionArea1.setId(null);
        assertThat(productionArea1).isNotEqualTo(productionArea2);
    }
}
