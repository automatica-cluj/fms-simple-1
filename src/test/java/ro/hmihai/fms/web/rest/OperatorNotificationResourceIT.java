package ro.hmihai.fms.web.rest;

import ro.hmihai.fms.IfmSimple1App;
import ro.hmihai.fms.domain.OperatorNotification;
import ro.hmihai.fms.repository.OperatorNotificationRepository;
import ro.hmihai.fms.service.OperatorNotificationService;
import ro.hmihai.fms.service.dto.OperatorNotificationDTO;
import ro.hmihai.fms.service.mapper.OperatorNotificationMapper;

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

import ro.hmihai.fms.domain.enumeration.NotificationResponse;
/**
 * Integration tests for the {@link OperatorNotificationResource} REST controller.
 */
@SpringBootTest(classes = IfmSimple1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class OperatorNotificationResourceIT {

    private static final Instant DEFAULT_RECEIVE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RECEIVE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_RESPONSE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RESPONSE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final NotificationResponse DEFAULT_OPERATOR_RESPONSE = NotificationResponse.ACCEPT;
    private static final NotificationResponse UPDATED_OPERATOR_RESPONSE = NotificationResponse.REJECT;

    @Autowired
    private OperatorNotificationRepository operatorNotificationRepository;

    @Autowired
    private OperatorNotificationMapper operatorNotificationMapper;

    @Autowired
    private OperatorNotificationService operatorNotificationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOperatorNotificationMockMvc;

    private OperatorNotification operatorNotification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperatorNotification createEntity(EntityManager em) {
        OperatorNotification operatorNotification = new OperatorNotification()
            .receiveDate(DEFAULT_RECEIVE_DATE)
            .responseDate(DEFAULT_RESPONSE_DATE)
            .operatorResponse(DEFAULT_OPERATOR_RESPONSE);
        return operatorNotification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperatorNotification createUpdatedEntity(EntityManager em) {
        OperatorNotification operatorNotification = new OperatorNotification()
            .receiveDate(UPDATED_RECEIVE_DATE)
            .responseDate(UPDATED_RESPONSE_DATE)
            .operatorResponse(UPDATED_OPERATOR_RESPONSE);
        return operatorNotification;
    }

    @BeforeEach
    public void initTest() {
        operatorNotification = createEntity(em);
    }

    @Test
    @Transactional
    public void createOperatorNotification() throws Exception {
        int databaseSizeBeforeCreate = operatorNotificationRepository.findAll().size();
        // Create the OperatorNotification
        OperatorNotificationDTO operatorNotificationDTO = operatorNotificationMapper.toDto(operatorNotification);
        restOperatorNotificationMockMvc.perform(post("/api/operator-notifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorNotificationDTO)))
            .andExpect(status().isCreated());

        // Validate the OperatorNotification in the database
        List<OperatorNotification> operatorNotificationList = operatorNotificationRepository.findAll();
        assertThat(operatorNotificationList).hasSize(databaseSizeBeforeCreate + 1);
        OperatorNotification testOperatorNotification = operatorNotificationList.get(operatorNotificationList.size() - 1);
        assertThat(testOperatorNotification.getReceiveDate()).isEqualTo(DEFAULT_RECEIVE_DATE);
        assertThat(testOperatorNotification.getResponseDate()).isEqualTo(DEFAULT_RESPONSE_DATE);
        assertThat(testOperatorNotification.getOperatorResponse()).isEqualTo(DEFAULT_OPERATOR_RESPONSE);
    }

    @Test
    @Transactional
    public void createOperatorNotificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = operatorNotificationRepository.findAll().size();

        // Create the OperatorNotification with an existing ID
        operatorNotification.setId(1L);
        OperatorNotificationDTO operatorNotificationDTO = operatorNotificationMapper.toDto(operatorNotification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperatorNotificationMockMvc.perform(post("/api/operator-notifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorNotificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OperatorNotification in the database
        List<OperatorNotification> operatorNotificationList = operatorNotificationRepository.findAll();
        assertThat(operatorNotificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOperatorNotifications() throws Exception {
        // Initialize the database
        operatorNotificationRepository.saveAndFlush(operatorNotification);

        // Get all the operatorNotificationList
        restOperatorNotificationMockMvc.perform(get("/api/operator-notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operatorNotification.getId().intValue())))
            .andExpect(jsonPath("$.[*].receiveDate").value(hasItem(DEFAULT_RECEIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].responseDate").value(hasItem(DEFAULT_RESPONSE_DATE.toString())))
            .andExpect(jsonPath("$.[*].operatorResponse").value(hasItem(DEFAULT_OPERATOR_RESPONSE.toString())));
    }
    
    @Test
    @Transactional
    public void getOperatorNotification() throws Exception {
        // Initialize the database
        operatorNotificationRepository.saveAndFlush(operatorNotification);

        // Get the operatorNotification
        restOperatorNotificationMockMvc.perform(get("/api/operator-notifications/{id}", operatorNotification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(operatorNotification.getId().intValue()))
            .andExpect(jsonPath("$.receiveDate").value(DEFAULT_RECEIVE_DATE.toString()))
            .andExpect(jsonPath("$.responseDate").value(DEFAULT_RESPONSE_DATE.toString()))
            .andExpect(jsonPath("$.operatorResponse").value(DEFAULT_OPERATOR_RESPONSE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingOperatorNotification() throws Exception {
        // Get the operatorNotification
        restOperatorNotificationMockMvc.perform(get("/api/operator-notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOperatorNotification() throws Exception {
        // Initialize the database
        operatorNotificationRepository.saveAndFlush(operatorNotification);

        int databaseSizeBeforeUpdate = operatorNotificationRepository.findAll().size();

        // Update the operatorNotification
        OperatorNotification updatedOperatorNotification = operatorNotificationRepository.findById(operatorNotification.getId()).get();
        // Disconnect from session so that the updates on updatedOperatorNotification are not directly saved in db
        em.detach(updatedOperatorNotification);
        updatedOperatorNotification
            .receiveDate(UPDATED_RECEIVE_DATE)
            .responseDate(UPDATED_RESPONSE_DATE)
            .operatorResponse(UPDATED_OPERATOR_RESPONSE);
        OperatorNotificationDTO operatorNotificationDTO = operatorNotificationMapper.toDto(updatedOperatorNotification);

        restOperatorNotificationMockMvc.perform(put("/api/operator-notifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorNotificationDTO)))
            .andExpect(status().isOk());

        // Validate the OperatorNotification in the database
        List<OperatorNotification> operatorNotificationList = operatorNotificationRepository.findAll();
        assertThat(operatorNotificationList).hasSize(databaseSizeBeforeUpdate);
        OperatorNotification testOperatorNotification = operatorNotificationList.get(operatorNotificationList.size() - 1);
        assertThat(testOperatorNotification.getReceiveDate()).isEqualTo(UPDATED_RECEIVE_DATE);
        assertThat(testOperatorNotification.getResponseDate()).isEqualTo(UPDATED_RESPONSE_DATE);
        assertThat(testOperatorNotification.getOperatorResponse()).isEqualTo(UPDATED_OPERATOR_RESPONSE);
    }

    @Test
    @Transactional
    public void updateNonExistingOperatorNotification() throws Exception {
        int databaseSizeBeforeUpdate = operatorNotificationRepository.findAll().size();

        // Create the OperatorNotification
        OperatorNotificationDTO operatorNotificationDTO = operatorNotificationMapper.toDto(operatorNotification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperatorNotificationMockMvc.perform(put("/api/operator-notifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorNotificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OperatorNotification in the database
        List<OperatorNotification> operatorNotificationList = operatorNotificationRepository.findAll();
        assertThat(operatorNotificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOperatorNotification() throws Exception {
        // Initialize the database
        operatorNotificationRepository.saveAndFlush(operatorNotification);

        int databaseSizeBeforeDelete = operatorNotificationRepository.findAll().size();

        // Delete the operatorNotification
        restOperatorNotificationMockMvc.perform(delete("/api/operator-notifications/{id}", operatorNotification.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OperatorNotification> operatorNotificationList = operatorNotificationRepository.findAll();
        assertThat(operatorNotificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
