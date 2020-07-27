package ro.hmihai.fms.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import ro.hmihai.fms.domain.enumeration.EventType;
import ro.hmihai.fms.domain.enumeration.ProcessingStatus;

/**
 * A DTO for the {@link ro.hmihai.fms.domain.FactoryEvent} entity.
 */
public class FactoryEventDTO implements Serializable {
    
    private Long id;

    private String subject;

    private String body;

    private EventType type;

    private Instant createDate;

    private Integer notificationCount;

    private Instant nextNotificationDate;

    private ProcessingStatus processingStatus;


    private Long productionAreaId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Integer getNotificationCount() {
        return notificationCount;
    }

    public void setNotificationCount(Integer notificationCount) {
        this.notificationCount = notificationCount;
    }

    public Instant getNextNotificationDate() {
        return nextNotificationDate;
    }

    public void setNextNotificationDate(Instant nextNotificationDate) {
        this.nextNotificationDate = nextNotificationDate;
    }

    public ProcessingStatus getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(ProcessingStatus processingStatus) {
        this.processingStatus = processingStatus;
    }

    public Long getProductionAreaId() {
        return productionAreaId;
    }

    public void setProductionAreaId(Long productionAreaId) {
        this.productionAreaId = productionAreaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactoryEventDTO)) {
            return false;
        }

        return id != null && id.equals(((FactoryEventDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FactoryEventDTO{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", body='" + getBody() + "'" +
            ", type='" + getType() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", notificationCount=" + getNotificationCount() +
            ", nextNotificationDate='" + getNextNotificationDate() + "'" +
            ", processingStatus='" + getProcessingStatus() + "'" +
            ", productionAreaId=" + getProductionAreaId() +
            "}";
    }
}
