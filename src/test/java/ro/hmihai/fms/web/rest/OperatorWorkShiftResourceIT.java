package ro.hmihai.fms.web.rest;

import ro.hmihai.fms.IfmSimple1App;
import ro.hmihai.fms.domain.OperatorWorkShift;
import ro.hmihai.fms.repository.OperatorWorkShiftRepository;
import ro.hmihai.fms.service.OperatorWorkShiftService;
import ro.hmihai.fms.service.dto.OperatorWorkShiftDTO;
import ro.hmihai.fms.service.mapper.OperatorWorkShiftMapper;

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

/**
 * Integration tests for the {@link OperatorWorkShiftResource} REST controller.
 */
@SpringBootTest(classes = IfmSimple1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class OperatorWorkShiftResourceIT {

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private OperatorWorkShiftRepository operatorWorkShiftRepository;

    @Autowired
    private OperatorWorkShiftMapper operatorWorkShiftMapper;

    @Autowired
    private OperatorWorkShiftService operatorWorkShiftService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOperatorWorkShiftMockMvc;

    private OperatorWorkShift operatorWorkShift;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperatorWorkShift createEntity(EntityManager em) {
        OperatorWorkShift operatorWorkShift = new OperatorWorkShift()
            .location(DEFAULT_LOCATION)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return operatorWorkShift;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperatorWorkShift createUpdatedEntity(EntityManager em) {
        OperatorWorkShift operatorWorkShift = new OperatorWorkShift()
            .location(UPDATED_LOCATION)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        return operatorWorkShift;
    }

    @BeforeEach
    public void initTest() {
        operatorWorkShift = createEntity(em);
    }

    @Test
    @Transactional
    public void createOperatorWorkShift() throws Exception {
        int databaseSizeBeforeCreate = operatorWorkShiftRepository.findAll().size();
        // Create the OperatorWorkShift
        OperatorWorkShiftDTO operatorWorkShiftDTO = operatorWorkShiftMapper.toDto(operatorWorkShift);
        restOperatorWorkShiftMockMvc.perform(post("/api/operator-work-shifts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorWorkShiftDTO)))
            .andExpect(status().isCreated());

        // Validate the OperatorWorkShift in the database
        List<OperatorWorkShift> operatorWorkShiftList = operatorWorkShiftRepository.findAll();
        assertThat(operatorWorkShiftList).hasSize(databaseSizeBeforeCreate + 1);
        OperatorWorkShift testOperatorWorkShift = operatorWorkShiftList.get(operatorWorkShiftList.size() - 1);
        assertThat(testOperatorWorkShift.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testOperatorWorkShift.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testOperatorWorkShift.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createOperatorWorkShiftWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = operatorWorkShiftRepository.findAll().size();

        // Create the OperatorWorkShift with an existing ID
        operatorWorkShift.setId(1L);
        OperatorWorkShiftDTO operatorWorkShiftDTO = operatorWorkShiftMapper.toDto(operatorWorkShift);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperatorWorkShiftMockMvc.perform(post("/api/operator-work-shifts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorWorkShiftDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OperatorWorkShift in the database
        List<OperatorWorkShift> operatorWorkShiftList = operatorWorkShiftRepository.findAll();
        assertThat(operatorWorkShiftList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOperatorWorkShifts() throws Exception {
        // Initialize the database
        operatorWorkShiftRepository.saveAndFlush(operatorWorkShift);

        // Get all the operatorWorkShiftList
        restOperatorWorkShiftMockMvc.perform(get("/api/operator-work-shifts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operatorWorkShift.getId().intValue())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getOperatorWorkShift() throws Exception {
        // Initialize the database
        operatorWorkShiftRepository.saveAndFlush(operatorWorkShift);

        // Get the operatorWorkShift
        restOperatorWorkShiftMockMvc.perform(get("/api/operator-work-shifts/{id}", operatorWorkShift.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(operatorWorkShift.getId().intValue()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingOperatorWorkShift() throws Exception {
        // Get the operatorWorkShift
        restOperatorWorkShiftMockMvc.perform(get("/api/operator-work-shifts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOperatorWorkShift() throws Exception {
        // Initialize the database
        operatorWorkShiftRepository.saveAndFlush(operatorWorkShift);

        int databaseSizeBeforeUpdate = operatorWorkShiftRepository.findAll().size();

        // Update the operatorWorkShift
        OperatorWorkShift updatedOperatorWorkShift = operatorWorkShiftRepository.findById(operatorWorkShift.getId()).get();
        // Disconnect from session so that the updates on updatedOperatorWorkShift are not directly saved in db
        em.detach(updatedOperatorWorkShift);
        updatedOperatorWorkShift
            .location(UPDATED_LOCATION)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);
        OperatorWorkShiftDTO operatorWorkShiftDTO = operatorWorkShiftMapper.toDto(updatedOperatorWorkShift);

        restOperatorWorkShiftMockMvc.perform(put("/api/operator-work-shifts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorWorkShiftDTO)))
            .andExpect(status().isOk());

        // Validate the OperatorWorkShift in the database
        List<OperatorWorkShift> operatorWorkShiftList = operatorWorkShiftRepository.findAll();
        assertThat(operatorWorkShiftList).hasSize(databaseSizeBeforeUpdate);
        OperatorWorkShift testOperatorWorkShift = operatorWorkShiftList.get(operatorWorkShiftList.size() - 1);
        assertThat(testOperatorWorkShift.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testOperatorWorkShift.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testOperatorWorkShift.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingOperatorWorkShift() throws Exception {
        int databaseSizeBeforeUpdate = operatorWorkShiftRepository.findAll().size();

        // Create the OperatorWorkShift
        OperatorWorkShiftDTO operatorWorkShiftDTO = operatorWorkShiftMapper.toDto(operatorWorkShift);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperatorWorkShiftMockMvc.perform(put("/api/operator-work-shifts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorWorkShiftDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OperatorWorkShift in the database
        List<OperatorWorkShift> operatorWorkShiftList = operatorWorkShiftRepository.findAll();
        assertThat(operatorWorkShiftList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOperatorWorkShift() throws Exception {
        // Initialize the database
        operatorWorkShiftRepository.saveAndFlush(operatorWorkShift);

        int databaseSizeBeforeDelete = operatorWorkShiftRepository.findAll().size();

        // Delete the operatorWorkShift
        restOperatorWorkShiftMockMvc.perform(delete("/api/operator-work-shifts/{id}", operatorWorkShift.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OperatorWorkShift> operatorWorkShiftList = operatorWorkShiftRepository.findAll();
        assertThat(operatorWorkShiftList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
