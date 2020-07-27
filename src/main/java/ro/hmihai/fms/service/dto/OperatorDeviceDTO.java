package ro.hmihai.fms.service.dto;

import java.io.Serializable;
import ro.hmihai.fms.domain.enumeration.DeviceType;

/**
 * A DTO for the {@link ro.hmihai.fms.domain.OperatorDevice} entity.
 */
public class OperatorDeviceDTO implements Serializable {
    
    private Long id;

    private String name;

    private DeviceType type;

    private String registrationId;

    
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

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperatorDeviceDTO)) {
            return false;
        }

        return id != null && id.equals(((OperatorDeviceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperatorDeviceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", registrationId='" + getRegistrationId() + "'" +
            "}";
    }
}
