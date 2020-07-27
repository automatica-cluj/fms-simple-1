package ro.hmihai.fms.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ro.hmihai.fms.domain.ProductionArea} entity.
 */
public class ProductionAreaDTO implements Serializable {
    
    private Long id;

    private String name;


    private Long operatorWorkShiftId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOperatorWorkShiftId() {
        return operatorWorkShiftId;
    }

    public void setOperatorWorkShiftId(Long operatorWorkShiftId) {
        this.operatorWorkShiftId = operatorWorkShiftId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductionAreaDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductionAreaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductionAreaDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", operatorWorkShiftId=" + getOperatorWorkShiftId() +
            "}";
    }
}
