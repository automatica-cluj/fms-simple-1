package ro.hmihai.fms.web.rest;

import ro.hmihai.fms.IfmSimple1App;
import ro.hmihai.fms.domain.OperatorDevice;
import ro.hmihai.fms.repository.OperatorDeviceRepository;
import ro.hmihai.fms.service.OperatorDeviceService;
import ro.hmihai.fms.service.dto.OperatorDeviceDTO;
import ro.hmihai.fms.service.mapper.OperatorDeviceMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ro.hmihai.fms.domain.enumeration.DeviceType;
/**
 * Integration tests for the {@link OperatorDeviceResource} REST controller.
 */
@SpringBootTest(classes = IfmSimple1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class OperatorDeviceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final DeviceType DEFAULT_TYPE = DeviceType.TIZEN;
    private static final DeviceType UPDATED_TYPE = DeviceType.ANDROID;

    private static final String DEFAULT_REGISTRATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION_ID = "BBBBBBBBBB";

    @Autowired
    private OperatorDeviceRepository operatorDeviceRepository;

    @Autowired
    private OperatorDeviceMapper operatorDeviceMapper;

    @Autowired
    private OperatorDeviceService operatorDeviceService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOperatorDeviceMockMvc;

    private OperatorDevice operatorDevice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperatorDevice createEntity(EntityManager em) {
        OperatorDevice operatorDevice = new OperatorDevice()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .registrationId(DEFAULT_REGISTRATION_ID);
        return operatorDevice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperatorDevice createUpdatedEntity(EntityManager em) {
        OperatorDevice operatorDevice = new OperatorDevice()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .registrationId(UPDATED_REGISTRATION_ID);
        return operatorDevice;
    }

    @BeforeEach
    public void initTest() {
        operatorDevice = createEntity(em);
    }

    @Test
    @Transactional
    public void createOperatorDevice() throws Exception {
        int databaseSizeBeforeCreate = operatorDeviceRepository.findAll().size();
        // Create the OperatorDevice
        OperatorDeviceDTO operatorDeviceDTO = operatorDeviceMapper.toDto(operatorDevice);
        restOperatorDeviceMockMvc.perform(post("/api/operator-devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorDeviceDTO)))
            .andExpect(status().isCreated());

        // Validate the OperatorDevice in the database
        List<OperatorDevice> operatorDeviceList = operatorDeviceRepository.findAll();
        assertThat(operatorDeviceList).hasSize(databaseSizeBeforeCreate + 1);
        OperatorDevice testOperatorDevice = operatorDeviceList.get(operatorDeviceList.size() - 1);
        assertThat(testOperatorDevice.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOperatorDevice.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testOperatorDevice.getRegistrationId()).isEqualTo(DEFAULT_REGISTRATION_ID);
    }

    @Test
    @Transactional
    public void createOperatorDeviceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = operatorDeviceRepository.findAll().size();

        // Create the OperatorDevice with an existing ID
        operatorDevice.setId(1L);
        OperatorDeviceDTO operatorDeviceDTO = operatorDeviceMapper.toDto(operatorDevice);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperatorDeviceMockMvc.perform(post("/api/operator-devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorDeviceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OperatorDevice in the database
        List<OperatorDevice> operatorDeviceList = operatorDeviceRepository.findAll();
        assertThat(operatorDeviceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOperatorDevices() throws Exception {
        // Initialize the database
        operatorDeviceRepository.saveAndFlush(operatorDevice);

        // Get all the operatorDeviceList
        restOperatorDeviceMockMvc.perform(get("/api/operator-devices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operatorDevice.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].registrationId").value(hasItem(DEFAULT_REGISTRATION_ID)));
    }
    
    @Test
    @Transactional
    public void getOperatorDevice() throws Exception {
        // Initialize the database
        operatorDeviceRepository.saveAndFlush(operatorDevice);

        // Get the operatorDevice
        restOperatorDeviceMockMvc.perform(get("/api/operator-devices/{id}", operatorDevice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(operatorDevice.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.registrationId").value(DEFAULT_REGISTRATION_ID));
    }
    @Test
    @Transactional
    public void getNonExistingOperatorDevice() throws Exception {
        // Get the operatorDevice
        restOperatorDeviceMockMvc.perform(get("/api/operator-devices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOperatorDevice() throws Exception {
        // Initialize the database
        operatorDeviceRepository.saveAndFlush(operatorDevice);

        int databaseSizeBeforeUpdate = operatorDeviceRepository.findAll().size();

        // Update the operatorDevice
        OperatorDevice updatedOperatorDevice = operatorDeviceRepository.findById(operatorDevice.getId()).get();
        // Disconnect from session so that the updates on updatedOperatorDevice are not directly saved in db
        em.detach(updatedOperatorDevice);
        updatedOperatorDevice
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .registrationId(UPDATED_REGISTRATION_ID);
        OperatorDeviceDTO operatorDeviceDTO = operatorDeviceMapper.toDto(updatedOperatorDevice);

        restOperatorDeviceMockMvc.perform(put("/api/operator-devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorDeviceDTO)))
            .andExpect(status().isOk());

        // Validate the OperatorDevice in the database
        List<OperatorDevice> operatorDeviceList = operatorDeviceRepository.findAll();
        assertThat(operatorDeviceList).hasSize(databaseSizeBeforeUpdate);
        OperatorDevice testOperatorDevice = operatorDeviceList.get(operatorDeviceList.size() - 1);
        assertThat(testOperatorDevice.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOperatorDevice.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testOperatorDevice.getRegistrationId()).isEqualTo(UPDATED_REGISTRATION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingOperatorDevice() throws Exception {
        int databaseSizeBeforeUpdate = operatorDeviceRepository.findAll().size();

        // Create the OperatorDevice
        OperatorDeviceDTO operatorDeviceDTO = operatorDeviceMapper.toDto(operatorDevice);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperatorDeviceMockMvc.perform(put("/api/operator-devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(operatorDeviceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OperatorDevice in the database
        List<OperatorDevice> operatorDeviceList = operatorDeviceRepository.findAll();
        assertThat(operatorDeviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOperatorDevice() throws Exception {
        // Initialize the database
        operatorDeviceRepository.saveAndFlush(operatorDevice);

        int databaseSizeBeforeDelete = operatorDeviceRepository.findAll().size();

        // Delete the operatorDevice
        restOperatorDeviceMockMvc.perform(delete("/api/operator-devices/{id}", operatorDevice.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OperatorDevice> operatorDeviceList = operatorDeviceRepository.findAll();
        assertThat(operatorDeviceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
