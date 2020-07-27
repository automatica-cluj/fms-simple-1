package ro.hmihai.fms.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ro.hmihai.fms.web.rest.TestUtil;

public class FactoryEventTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FactoryEvent.class);
        FactoryEvent factoryEvent1 = new FactoryEvent();
        factoryEvent1.setId(1L);
        FactoryEvent factoryEvent2 = new FactoryEvent();
        factoryEvent2.setId(factoryEvent1.getId());
        assertThat(factoryEvent1).isEqualTo(factoryEvent2);
        factoryEvent2.setId(2L);
        assertThat(factoryEvent1).isNotEqualTo(factoryEvent2);
        factoryEvent1.setId(null);
        assertThat(factoryEvent1).isNotEqualTo(factoryEvent2);
    }
}
