package ro.hmihai.fms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import ro.hmihai.fms.domain.enumeration.EventType;

import ro.hmihai.fms.domain.enumeration.ProcessingStatus;

/**
 * A FactoryEvent.
 */
@Entity
@Table(name = "factory_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FactoryEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EventType type;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "notification_count")
    private Integer notificationCount;

    @Column(name = "next_notification_date")
    private Instant nextNotificationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "processing_status")
    private ProcessingStatus processingStatus;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private ProductionArea productionArea;

    @OneToMany(mappedBy = "factoryEvent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<OperatorNotification> notifications = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public FactoryEvent subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public FactoryEvent body(String body) {
        this.body = body;
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EventType getType() {
        return type;
    }

    public FactoryEvent type(EventType type) {
        this.type = type;
        return this;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public FactoryEvent createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Integer getNotificationCount() {
        return notificationCount;
    }

    public FactoryEvent notificationCount(Integer notificationCount) {
        this.notificationCount = notificationCount;
        return this;
    }

    public void setNotificationCount(Integer notificationCount) {
        this.notificationCount = notificationCount;
    }

    public Instant getNextNotificationDate() {
        return nextNotificationDate;
    }

    public FactoryEvent nextNotificationDate(Instant nextNotificationDate) {
        this.nextNotificationDate = nextNotificationDate;
        return this;
    }

    public void setNextNotificationDate(Instant nextNotificationDate) {
        this.nextNotificationDate = nextNotificationDate;
    }

    public ProcessingStatus getProcessingStatus() {
        return processingStatus;
    }

    public FactoryEvent processingStatus(ProcessingStatus processingStatus) {
        this.processingStatus = processingStatus;
        return this;
    }

    public void setProcessingStatus(ProcessingStatus processingStatus) {
        this.processingStatus = processingStatus;
    }

    public ProductionArea getProductionArea() {
        return productionArea;
    }

    public FactoryEvent productionArea(ProductionArea productionArea) {
        this.productionArea = productionArea;
        return this;
    }

    public void setProductionArea(ProductionArea productionArea) {
        this.productionArea = productionArea;
    }

    public Set<OperatorNotification> getNotifications() {
        return notifications;
    }

    public FactoryEvent notifications(Set<OperatorNotification> operatorNotifications) {
        this.notifications = operatorNotifications;
        return this;
    }

    public FactoryEvent addNotification(OperatorNotification operatorNotification) {
        this.notifications.add(operatorNotification);
        operatorNotification.setFactoryEvent(this);
        return this;
    }

    public FactoryEvent removeNotification(OperatorNotification operatorNotification) {
        this.notifications.remove(operatorNotification);
        operatorNotification.setFactoryEvent(null);
        return this;
    }

    public void setNotifications(Set<OperatorNotification> operatorNotifications) {
        this.notifications = operatorNotifications;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactoryEvent)) {
            return false;
        }
        return id != null && id.equals(((FactoryEvent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FactoryEvent{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", body='" + getBody() + "'" +
            ", type='" + getType() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", notificationCount=" + getNotificationCount() +
            ", nextNotificationDate='" + getNextNotificationDate() + "'" +
            ", processingStatus='" + getProcessingStatus() + "'" +
            "}";
    }
}
