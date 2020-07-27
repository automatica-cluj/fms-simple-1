package ro.hmihai.fms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ProductionArea.
 */
@Entity
@Table(name = "production_area")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductionArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties(value = "productionAreas", allowSetters = true)
    private OperatorWorkShift operatorWorkShift;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ProductionArea name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OperatorWorkShift getOperatorWorkShift() {
        return operatorWorkShift;
    }

    public ProductionArea operatorWorkShift(OperatorWorkShift operatorWorkShift) {
        this.operatorWorkShift = operatorWorkShift;
        return this;
    }

    public void setOperatorWorkShift(OperatorWorkShift operatorWorkShift) {
        this.operatorWorkShift = operatorWorkShift;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductionArea)) {
            return false;
        }
        return id != null && id.equals(((ProductionArea) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductionArea{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
