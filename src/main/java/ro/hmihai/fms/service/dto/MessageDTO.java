package ro.hmihai.fms.service.dto;

import java.io.Serializable;
import ro.hmihai.fms.domain.enumeration.MessageStatus;

/**
 * A DTO for the {@link ro.hmihai.fms.domain.Message} entity.
 */
public class MessageDTO implements Serializable {
    
    private Long id;

    private String subject;

    private String content;

    private MessageStatus status;


    private Long operatorWorkShiftId;
    
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
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
        if (!(o instanceof MessageDTO)) {
            return false;
        }

        return id != null && id.equals(((MessageDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageDTO{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", content='" + getContent() + "'" +
            ", status='" + getStatus() + "'" +
            ", operatorWorkShiftId=" + getOperatorWorkShiftId() +
            "}";
    }
}
