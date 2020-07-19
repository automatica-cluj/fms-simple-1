package ro.hmihai.fms.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ro.hmihai.fms.domain.Device} entity.
 */
public class DeviceDTO implements Serializable {
    
    private Long id;

    private String registrationId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof DeviceDTO)) {
            return false;
        }

        return id != null && id.equals(((DeviceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeviceDTO{" +
            "id=" + getId() +
            ", registrationId='" + getRegistrationId() + "'" +
            "}";
    }
}
