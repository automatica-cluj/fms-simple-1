package ro.hmihai.fms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "location")
    private String location;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @OneToOne
    @JoinColumn(unique = true)
    private Device device;

    @OneToOne
    @JoinColumn(unique = true)
    private Operator operator;

    @OneToMany(mappedBy = "operatorWorkShift")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Message> messages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public OperatorWorkShift location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Device getDevice() {
        return device;
    }

    public OperatorWorkShift device(Device device) {
        this.device = device;
        return this;
    }

    public void setDevice(Device device) {
        this.device = device;
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

    public Set<Message> getMessages() {
        return messages;
    }

    public OperatorWorkShift messages(Set<Message> messages) {
        this.messages = messages;
        return this;
    }

    public OperatorWorkShift addMessage(Message message) {
        this.messages.add(message);
        message.setOperatorWorkShift(this);
        return this;
    }

    public OperatorWorkShift removeMessage(Message message) {
        this.messages.remove(message);
        message.setOperatorWorkShift(null);
        return this;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
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
            ", location='" + getLocation() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
