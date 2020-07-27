package ro.hmihai.fms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import ro.hmihai.fms.domain.enumeration.NotificationResponse;

/**
 * A OperatorNotification.
 */
@Entity
@Table(name = "operator_notification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OperatorNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "receive_date")
    private Instant receiveDate;

    @Column(name = "response_date")
    private Instant responseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "operator_response")
    private NotificationResponse operatorResponse;

    @ManyToOne
    @JsonIgnoreProperties(value = "notifications", allowSetters = true)
    private OperatorWorkShift operatorWorkShift;

    @ManyToOne
    @JsonIgnoreProperties(value = "notifications", allowSetters = true)
    private FactoryEvent factoryEvent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getReceiveDate() {
        return receiveDate;
    }

    public OperatorNotification receiveDate(Instant receiveDate) {
        this.receiveDate = receiveDate;
        return this;
    }

    public void setReceiveDate(Instant receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Instant getResponseDate() {
        return responseDate;
    }

    public OperatorNotification responseDate(Instant responseDate) {
        this.responseDate = responseDate;
        return this;
    }

    public void setResponseDate(Instant responseDate) {
        this.responseDate = responseDate;
    }

    public NotificationResponse getOperatorResponse() {
        return operatorResponse;
    }

    public OperatorNotification operatorResponse(NotificationResponse operatorResponse) {
        this.operatorResponse = operatorResponse;
        return this;
    }

    public void setOperatorResponse(NotificationResponse operatorResponse) {
        this.operatorResponse = operatorResponse;
    }

    public OperatorWorkShift getOperatorWorkShift() {
        return operatorWorkShift;
    }

    public OperatorNotification operatorWorkShift(OperatorWorkShift operatorWorkShift) {
        this.operatorWorkShift = operatorWorkShift;
        return this;
    }

    public void setOperatorWorkShift(OperatorWorkShift operatorWorkShift) {
        this.operatorWorkShift = operatorWorkShift;
    }

    public FactoryEvent getFactoryEvent() {
        return factoryEvent;
    }

    public OperatorNotification factoryEvent(FactoryEvent factoryEvent) {
        this.factoryEvent = factoryEvent;
        return this;
    }

    public void setFactoryEvent(FactoryEvent factoryEvent) {
        this.factoryEvent = factoryEvent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperatorNotification)) {
            return false;
        }
        return id != null && id.equals(((OperatorNotification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperatorNotification{" +
            "id=" + getId() +
            ", receiveDate='" + getReceiveDate() + "'" +
            ", responseDate='" + getResponseDate() + "'" +
            ", operatorResponse='" + getOperatorResponse() + "'" +
            "}";
    }
}
