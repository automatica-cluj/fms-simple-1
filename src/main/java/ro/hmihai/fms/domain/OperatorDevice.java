package ro.hmihai.fms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import ro.hmihai.fms.domain.enumeration.DeviceType;

/**
 * A OperatorDevice.
 */
@Entity
@Table(name = "operator_device")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OperatorDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private DeviceType type;

    @Column(name = "registration_id")
    private String registrationId;

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

    public OperatorDevice name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public OperatorDevice type(DeviceType type) {
        this.type = type;
        return this;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public OperatorDevice registrationId(String registrationId) {
        this.registrationId = registrationId;
        return this;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperatorDevice)) {
            return false;
        }
        return id != null && id.equals(((OperatorDevice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperatorDevice{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", registrationId='" + getRegistrationId() + "'" +
            "}";
    }
}
