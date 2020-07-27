package ro.hmihai.fms.service.dto;

import java.time.Instant;
import java.io.Serializable;
import ro.hmihai.fms.domain.enumeration.NotificationResponse;

/**
 * A DTO for the {@link ro.hmihai.fms.domain.OperatorNotification} entity.
 */
public class OperatorNotificationDTO implements Serializable {
    
    private Long id;

    private Instant receiveDate;

    private Instant responseDate;

    private NotificationResponse operatorResponse;


    private Long operatorWorkShiftId;

    private Long factoryEventId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Instant receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Instant getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Instant responseDate) {
        this.responseDate = responseDate;
    }

    public NotificationResponse getOperatorResponse() {
        return operatorResponse;
    }

    public void setOperatorResponse(NotificationResponse operatorResponse) {
        this.operatorResponse = operatorResponse;
    }

    public Long getOperatorWorkShiftId() {
        return operatorWorkShiftId;
    }

    public void setOperatorWorkShiftId(Long operatorWorkShiftId) {
        this.operatorWorkShiftId = operatorWorkShiftId;
    }

    public Long getFactoryEventId() {
        return factoryEventId;
    }

    public void setFactoryEventId(Long factoryEventId) {
        this.factoryEventId = factoryEventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperatorNotificationDTO)) {
            return false;
        }

        return id != null && id.equals(((OperatorNotificationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperatorNotificationDTO{" +
            "id=" + getId() +
            ", receiveDate='" + getReceiveDate() + "'" +
            ", responseDate='" + getResponseDate() + "'" +
            ", operatorResponse='" + getOperatorResponse() + "'" +
            ", operatorWorkShiftId=" + getOperatorWorkShiftId() +
            ", factoryEventId=" + getFactoryEventId() +
            "}";
    }
}
