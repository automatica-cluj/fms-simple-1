package ro.hmihai.fms.web.rest;

import ro.hmihai.fms.IfmSimple1App;
import ro.hmihai.fms.domain.FactoryEvent;
import ro.hmihai.fms.domain.ProductionArea;
import ro.hmihai.fms.repository.FactoryEventRepository;
import ro.hmihai.fms.service.FactoryEventService;
import ro.hmihai.fms.service.dto.FactoryEventDTO;
import ro.hmihai.fms.service.mapper.FactoryEventMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ro.hmihai.fms.domain.enumeration.EventType;
import ro.hmihai.fms.domain.enumeration.ProcessingStatus;
/**
 * Integration tests for the {@link FactoryEventResource} REST controller.
 */
@SpringBootTest(classes = IfmSimple1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class FactoryEventResourceIT {

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_BODY = "AAAAAAAAAA";
    private static final String UPDATED_BODY = "BBBBBBBBBB";

    private static final EventType DEFAULT_TYPE = EventType.MACHINE_EVENT;
    private static final EventType UPDATED_TYPE = EventType.USER_MESSAGE;

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_NOTIFICATION_COUNT = 1;
    private static final Integer UPDATED_NOTIFICATION_COUNT = 2;

    private static final Instant DEFAULT_NEXT_NOTIFICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NEXT_NOTIFICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final ProcessingStatus DEFAULT_PROCESSING_STATUS = ProcessingStatus.NEW;
    private static final ProcessingStatus UPDATED_PROCESSING_STATUS = ProcessingStatus.WAIT_ACK;

    @Autowired
    private FactoryEventRepository factoryEventRepository;

    @Autowired
    private FactoryEventMapper factoryEventMapper;

    @Autowired
    private FactoryEventService factoryEventService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFactoryEventMockMvc;

    private FactoryEvent factoryEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FactoryEvent createEntity(EntityManager em) {
        FactoryEvent factoryEvent = new FactoryEvent()
            .subject(DEFAULT_SUBJECT)
            .body(DEFAULT_BODY)
            .type(DEFAULT_TYPE)
            .createDate(DEFAULT_CREATE_DATE)
            .notificationCount(DEFAULT_NOTIFICATION_COUNT)
            .nextNotificationDate(DEFAULT_NEXT_NOTIFICATION_DATE)
            .processingStatus(DEFAULT_PROCESSING_STATUS);
        // Add required entity
        ProductionArea productionArea;
        if (TestUtil.findAll(em, ProductionArea.class).isEmpty()) {
            productionArea = ProductionAreaResourceIT.createEntity(em);
            em.persist(productionArea);
            em.flush();
        } else {
            productionArea = TestUtil.findAll(em, ProductionArea.class).get(0);
        }
        factoryEvent.setProductionArea(productionArea);
        return factoryEvent;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FactoryEvent createUpdatedEntity(EntityManager em) {
        FactoryEvent factoryEvent = new FactoryEvent()
            .subject(UPDATED_SUBJECT)
            .body(UPDATED_BODY)
            .type(UPDATED_TYPE)
            .createDate(UPDATED_CREATE_DATE)
            .notificationCount(UPDATED_NOTIFICATION_COUNT)
            .nextNotificationDate(UPDATED_NEXT_NOTIFICATION_DATE)
            .processingStatus(UPDATED_PROCESSING_STATUS);
        // Add required entity
        ProductionArea productionArea;
        if (TestUtil.findAll(em, ProductionArea.class).isEmpty()) {
            productionArea = ProductionAreaResourceIT.createUpdatedEntity(em);
            em.persist(productionArea);
            em.flush();
        } else {
            productionArea = TestUtil.findAll(em, ProductionArea.class).get(0);
        }
        factoryEvent.setProductionArea(productionArea);
        return factoryEvent;
    }

    @BeforeEach
    public void initTest() {
        factoryEvent = createEntity(em);
    }

    @Test
    @Transactional
    public void createFactoryEvent() throws Exception {
        int databaseSizeBeforeCreate = factoryEventRepository.findAll().size();
        // Create the FactoryEvent
        FactoryEventDTO factoryEventDTO = factoryEventMapper.toDto(factoryEvent);
        restFactoryEventMockMvc.perform(post("/api/factory-events")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factoryEventDTO)))
            .andExpect(status().isCreated());

        // Validate the FactoryEvent in the database
        List<FactoryEvent> factoryEventList = factoryEventRepository.findAll();
        assertThat(factoryEventList).hasSize(databaseSizeBeforeCreate + 1);
        FactoryEvent testFactoryEvent = factoryEventList.get(factoryEventList.size() - 1);
        assertThat(testFactoryEvent.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testFactoryEvent.getBody()).isEqualTo(DEFAULT_BODY);
        assertThat(testFactoryEvent.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFactoryEvent.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testFactoryEvent.getNotificationCount()).isEqualTo(DEFAULT_NOTIFICATION_COUNT);
        assertThat(testFactoryEvent.getNextNotificationDate()).isEqualTo(DEFAULT_NEXT_NOTIFICATION_DATE);
        assertThat(testFactoryEvent.getProcessingStatus()).isEqualTo(DEFAULT_PROCESSING_STATUS);
    }

    @Test
    @Transactional
    public void createFactoryEventWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = factoryEventRepository.findAll().size();

        // Create the FactoryEvent with an existing ID
        factoryEvent.setId(1L);
        FactoryEventDTO factoryEventDTO = factoryEventMapper.toDto(factoryEvent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFactoryEventMockMvc.perform(post("/api/factory-events")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factoryEventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FactoryEvent in the database
        List<FactoryEvent> factoryEventList = factoryEventRepository.findAll();
        assertThat(factoryEventList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFactoryEvents() throws Exception {
        // Initialize the database
        factoryEventRepository.saveAndFlush(factoryEvent);

        // Get all the factoryEventList
        restFactoryEventMockMvc.perform(get("/api/factory-events?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(factoryEvent.getId().intValue())))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].notificationCount").value(hasItem(DEFAULT_NOTIFICATION_COUNT)))
            .andExpect(jsonPath("$.[*].nextNotificationDate").value(hasItem(DEFAULT_NEXT_NOTIFICATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].processingStatus").value(hasItem(DEFAULT_PROCESSING_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getFactoryEvent() throws Exception {
        // Initialize the database
        factoryEventRepository.saveAndFlush(factoryEvent);

        // Get the factoryEvent
        restFactoryEventMockMvc.perform(get("/api/factory-events/{id}", factoryEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(factoryEvent.getId().intValue()))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.body").value(DEFAULT_BODY))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.notificationCount").value(DEFAULT_NOTIFICATION_COUNT))
            .andExpect(jsonPath("$.nextNotificationDate").value(DEFAULT_NEXT_NOTIFICATION_DATE.toString()))
            .andExpect(jsonPath("$.processingStatus").value(DEFAULT_PROCESSING_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFactoryEvent() throws Exception {
        // Get the factoryEvent
        restFactoryEventMockMvc.perform(get("/api/factory-events/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFactoryEvent() throws Exception {
        // Initialize the database
        factoryEventRepository.saveAndFlush(factoryEvent);

        int databaseSizeBeforeUpdate = factoryEventRepository.findAll().size();

        // Update the factoryEvent
        FactoryEvent updatedFactoryEvent = factoryEventRepository.findById(factoryEvent.getId()).get();
        // Disconnect from session so that the updates on updatedFactoryEvent are not directly saved in db
        em.detach(updatedFactoryEvent);
        updatedFactoryEvent
            .subject(UPDATED_SUBJECT)
            .body(UPDATED_BODY)
            .type(UPDATED_TYPE)
            .createDate(UPDATED_CREATE_DATE)
            .notificationCount(UPDATED_NOTIFICATION_COUNT)
            .nextNotificationDate(UPDATED_NEXT_NOTIFICATION_DATE)
            .processingStatus(UPDATED_PROCESSING_STATUS);
        FactoryEventDTO factoryEventDTO = factoryEventMapper.toDto(updatedFactoryEvent);

        restFactoryEventMockMvc.perform(put("/api/factory-events")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factoryEventDTO)))
            .andExpect(status().isOk());

        // Validate the FactoryEvent in the database
        List<FactoryEvent> factoryEventList = factoryEventRepository.findAll();
        assertThat(factoryEventList).hasSize(databaseSizeBeforeUpdate);
        FactoryEvent testFactoryEvent = factoryEventList.get(factoryEventList.size() - 1);
        assertThat(testFactoryEvent.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testFactoryEvent.getBody()).isEqualTo(UPDATED_BODY);
        assertThat(testFactoryEvent.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFactoryEvent.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testFactoryEvent.getNotificationCount()).isEqualTo(UPDATED_NOTIFICATION_COUNT);
        assertThat(testFactoryEvent.getNextNotificationDate()).isEqualTo(UPDATED_NEXT_NOTIFICATION_DATE);
        assertThat(testFactoryEvent.getProcessingStatus()).isEqualTo(UPDATED_PROCESSING_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingFactoryEvent() throws Exception {
        int databaseSizeBeforeUpdate = factoryEventRepository.findAll().size();

        // Create the FactoryEvent
        FactoryEventDTO factoryEventDTO = factoryEventMapper.toDto(factoryEvent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFactoryEventMockMvc.perform(put("/api/factory-events")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factoryEventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FactoryEvent in the database
        List<FactoryEvent> factoryEventList = factoryEventRepository.findAll();
        assertThat(factoryEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFactoryEvent() throws Exception {
        // Initialize the database
        factoryEventRepository.saveAndFlush(factoryEvent);

        int databaseSizeBeforeDelete = factoryEventRepository.findAll().size();

        // Delete the factoryEvent
        restFactoryEventMockMvc.perform(delete("/api/factory-events/{id}", factoryEvent.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FactoryEvent> factoryEventList = factoryEventRepository.findAll();
        assertThat(factoryEventList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
