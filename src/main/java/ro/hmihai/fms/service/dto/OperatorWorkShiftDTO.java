package ro.hmihai.fms.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import ro.hmihai.fms.domain.enumeration.WorkShiftStatus;

/**
 * A DTO for the {@link ro.hmihai.fms.domain.OperatorWorkShift} entity.
 */
public class OperatorWorkShiftDTO implements Serializable {
    
    private Long id;

    private Instant startDate;

    private Instant endDate;

    private WorkShiftStatus status;


    private Long deviceId;

    private Long operatorId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public WorkShiftStatus getStatus() {
        return status;
    }

    public void setStatus(WorkShiftStatus status) {
        this.status = status;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long operatorDeviceId) {
        this.deviceId = operatorDeviceId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperatorWorkShiftDTO)) {
            return false;
        }

        return id != null && id.equals(((OperatorWorkShiftDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperatorWorkShiftDTO{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", deviceId=" + getDeviceId() +
            ", operatorId=" + getOperatorId() +
            "}";
    }
}
