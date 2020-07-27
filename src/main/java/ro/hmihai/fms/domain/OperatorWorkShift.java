package ro.hmihai.fms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import ro.hmihai.fms.domain.enumeration.WorkShiftStatus;

/**
 * A OperatorWorkShift.
 */
@Entity
@Table(name = "operator_work_shift")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OperatorWorkShift implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private WorkShiftStatus status;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private OperatorDevice device;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Operator operator;

    @OneToMany(mappedBy = "operatorWorkShift")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<OperatorNotification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "operatorWorkShift")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ProductionArea> productionAreas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public OperatorWorkShift startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public OperatorWorkShift endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public WorkShiftStatus getStatus() {
        return status;
    }

    public OperatorWorkShift status(WorkShiftStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(WorkShiftStatus status) {
        this.status = status;
    }

    public OperatorDevice getDevice() {
        return device;
    }

    public OperatorWorkShift device(OperatorDevice operatorDevice) {
        this.device = operatorDevice;
        return this;
    }

    public void setDevice(OperatorDevice operatorDevice) {
        this.device = operatorDevice;
    }

    public Operator getOperator() {
        return operator;
    }

    public OperatorWorkShift operator(Operator operator) {
        this.operator = operator;
        return this;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Set<OperatorNotification> getNotifications() {
        return notifications;
    }

    public OperatorWorkShift notifications(Set<OperatorNotification> operatorNotifications) {
        this.notifications = operatorNotifications;
        return this;
    }

    public OperatorWorkShift addNotification(OperatorNotification operatorNotification) {
        this.notifications.add(operatorNotification);
        operatorNotification.setOperatorWorkShift(this);
        return this;
    }

    public OperatorWorkShift removeNotification(OperatorNotification operatorNotification) {
        this.notifications.remove(operatorNotification);
        operatorNotification.setOperatorWorkShift(null);
        return this;
    }

    public void setNotifications(Set<OperatorNotification> operatorNotifications) {
        this.notifications = operatorNotifications;
    }

    public Set<ProductionArea> getProductionAreas() {
        return productionAreas;
    }

    public OperatorWorkShift productionAreas(Set<ProductionArea> productionAreas) {
        this.productionAreas = productionAreas;
        return this;
    }

    public OperatorWorkShift addProductionArea(ProductionArea productionArea) {
        this.productionAreas.add(productionArea);
        productionArea.setOperatorWorkShift(this);
        return this;
    }

    public OperatorWorkShift removeProductionArea(ProductionArea productionArea) {
        this.productionAreas.remove(productionArea);
        productionArea.setOperatorWorkShift(null);
        return this;
    }

    public void setProductionAreas(Set<ProductionArea> productionAreas) {
        this.productionAreas = productionAreas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperatorWorkShift)) {
            return false;
        }
        return id != null && id.equals(((OperatorWorkShift) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperatorWorkShift{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
