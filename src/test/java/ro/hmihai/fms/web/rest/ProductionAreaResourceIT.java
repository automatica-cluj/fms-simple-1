package ro.hmihai.fms.web.rest;

import ro.hmihai.fms.IfmSimple1App;
import ro.hmihai.fms.domain.ProductionArea;
import ro.hmihai.fms.repository.ProductionAreaRepository;
import ro.hmihai.fms.service.ProductionAreaService;
import ro.hmihai.fms.service.dto.ProductionAreaDTO;
import ro.hmihai.fms.service.mapper.ProductionAreaMapper;

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

/**
 * Integration tests for the {@link ProductionAreaResource} REST controller.
 */
@SpringBootTest(classes = IfmSimple1App.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProductionAreaResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ProductionAreaRepository productionAreaRepository;

    @Autowired
    private ProductionAreaMapper productionAreaMapper;

    @Autowired
    private ProductionAreaService productionAreaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductionAreaMockMvc;

    private ProductionArea productionArea;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductionArea createEntity(EntityManager em) {
        ProductionArea productionArea = new ProductionArea()
            .name(DEFAULT_NAME);
        return productionArea;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductionArea createUpdatedEntity(EntityManager em) {
        ProductionArea productionArea = new ProductionArea()
            .name(UPDATED_NAME);
        return productionArea;
    }

    @BeforeEach
    public void initTest() {
        productionArea = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductionArea() throws Exception {
        int databaseSizeBeforeCreate = productionAreaRepository.findAll().size();
        // Create the ProductionArea
        ProductionAreaDTO productionAreaDTO = productionAreaMapper.toDto(productionArea);
        restProductionAreaMockMvc.perform(post("/api/production-areas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productionAreaDTO)))
            .andExpect(status().isCreated());

        // Validate the ProductionArea in the database
        List<ProductionArea> productionAreaList = productionAreaRepository.findAll();
        assertThat(productionAreaList).hasSize(databaseSizeBeforeCreate + 1);
        ProductionArea testProductionArea = productionAreaList.get(productionAreaList.size() - 1);
        assertThat(testProductionArea.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createProductionAreaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productionAreaRepository.findAll().size();

        // Create the ProductionArea with an existing ID
        productionArea.setId(1L);
        ProductionAreaDTO productionAreaDTO = productionAreaMapper.toDto(productionArea);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductionAreaMockMvc.perform(post("/api/production-areas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productionAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductionArea in the database
        List<ProductionArea> productionAreaList = productionAreaRepository.findAll();
        assertThat(productionAreaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProductionAreas() throws Exception {
        // Initialize the database
        productionAreaRepository.saveAndFlush(productionArea);

        // Get all the productionAreaList
        restProductionAreaMockMvc.perform(get("/api/production-areas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productionArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getProductionArea() throws Exception {
        // Initialize the database
        productionAreaRepository.saveAndFlush(productionArea);

        // Get the productionArea
        restProductionAreaMockMvc.perform(get("/api/production-areas/{id}", productionArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productionArea.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingProductionArea() throws Exception {
        // Get the productionArea
        restProductionAreaMockMvc.perform(get("/api/production-areas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductionArea() throws Exception {
        // Initialize the database
        productionAreaRepository.saveAndFlush(productionArea);

        int databaseSizeBeforeUpdate = productionAreaRepository.findAll().size();

        // Update the productionArea
        ProductionArea updatedProductionArea = productionAreaRepository.findById(productionArea.getId()).get();
        // Disconnect from session so that the updates on updatedProductionArea are not directly saved in db
        em.detach(updatedProductionArea);
        updatedProductionArea
            .name(UPDATED_NAME);
        ProductionAreaDTO productionAreaDTO = productionAreaMapper.toDto(updatedProductionArea);

        restProductionAreaMockMvc.perform(put("/api/production-areas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productionAreaDTO)))
            .andExpect(status().isOk());

        // Validate the ProductionArea in the database
        List<ProductionArea> productionAreaList = productionAreaRepository.findAll();
        assertThat(productionAreaList).hasSize(databaseSizeBeforeUpdate);
        ProductionArea testProductionArea = productionAreaList.get(productionAreaList.size() - 1);
        assertThat(testProductionArea.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingProductionArea() throws Exception {
        int databaseSizeBeforeUpdate = productionAreaRepository.findAll().size();

        // Create the ProductionArea
        ProductionAreaDTO productionAreaDTO = productionAreaMapper.toDto(productionArea);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductionAreaMockMvc.perform(put("/api/production-areas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(productionAreaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductionArea in the database
        List<ProductionArea> productionAreaList = productionAreaRepository.findAll();
        assertThat(productionAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductionArea() throws Exception {
        // Initialize the database
        productionAreaRepository.saveAndFlush(productionArea);

        int databaseSizeBeforeDelete = productionAreaRepository.findAll().size();

        // Delete the productionArea
        restProductionAreaMockMvc.perform(delete("/api/production-areas/{id}", productionArea.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProductionArea> productionAreaList = productionAreaRepository.findAll();
        assertThat(productionAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
