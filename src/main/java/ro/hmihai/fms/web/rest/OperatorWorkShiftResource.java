package ro.hmihai.fms.web.rest;

import ro.hmihai.fms.service.OperatorWorkShiftService;
import ro.hmihai.fms.web.rest.errors.BadRequestAlertException;
import ro.hmihai.fms.service.dto.OperatorWorkShiftDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ro.hmihai.fms.domain.OperatorWorkShift}.
 */
@RestController
@RequestMapping("/api")
public class OperatorWorkShiftResource {

    private final Logger log = LoggerFactory.getLogger(OperatorWorkShiftResource.class);

    private static final String ENTITY_NAME = "operatorWorkShift";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperatorWorkShiftService operatorWorkShiftService;

    public OperatorWorkShiftResource(OperatorWorkShiftService operatorWorkShiftService) {
        this.operatorWorkShiftService = operatorWorkShiftService;
    }

    /**
     * {@code POST  /operator-work-shifts} : Create a new operatorWorkShift.
     *
     * @param operatorWorkShiftDTO the operatorWorkShiftDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operatorWorkShiftDTO, or with status {@code 400 (Bad Request)} if the operatorWorkShift has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operator-work-shifts")
    public ResponseEntity<OperatorWorkShiftDTO> createOperatorWorkShift(@RequestBody OperatorWorkShiftDTO operatorWorkShiftDTO) throws URISyntaxException {
        log.debug("REST request to save OperatorWorkShift : {}", operatorWorkShiftDTO);
        if (operatorWorkShiftDTO.getId() != null) {
            throw new BadRequestAlertException("A new operatorWorkShift cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperatorWorkShiftDTO result = operatorWorkShiftService.save(operatorWorkShiftDTO);
        return ResponseEntity.created(new URI("/api/operator-work-shifts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operator-work-shifts} : Updates an existing operatorWorkShift.
     *
     * @param operatorWorkShiftDTO the operatorWorkShiftDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operatorWorkShiftDTO,
     * or with status {@code 400 (Bad Request)} if the operatorWorkShiftDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operatorWorkShiftDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operator-work-shifts")
    public ResponseEntity<OperatorWorkShiftDTO> updateOperatorWorkShift(@RequestBody OperatorWorkShiftDTO operatorWorkShiftDTO) throws URISyntaxException {
        log.debug("REST request to update OperatorWorkShift : {}", operatorWorkShiftDTO);
        if (operatorWorkShiftDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OperatorWorkShiftDTO result = operatorWorkShiftService.save(operatorWorkShiftDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operatorWorkShiftDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /operator-work-shifts} : get all the operatorWorkShifts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operatorWorkShifts in body.
     */
    @GetMapping("/operator-work-shifts")
    public ResponseEntity<List<OperatorWorkShiftDTO>> getAllOperatorWorkShifts(Pageable pageable) {
        log.debug("REST request to get a page of OperatorWorkShifts");
        Page<OperatorWorkShiftDTO> page = operatorWorkShiftService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /operator-work-shifts/:id} : get the "id" operatorWorkShift.
     *
     * @param id the id of the operatorWorkShiftDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operatorWorkShiftDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operator-work-shifts/{id}")
    public ResponseEntity<OperatorWorkShiftDTO> getOperatorWorkShift(@PathVariable Long id) {
        log.debug("REST request to get OperatorWorkShift : {}", id);
        Optional<OperatorWorkShiftDTO> operatorWorkShiftDTO = operatorWorkShiftService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operatorWorkShiftDTO);
    }

    /**
     * {@code DELETE  /operator-work-shifts/:id} : delete the "id" operatorWorkShift.
     *
     * @param id the id of the operatorWorkShiftDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operator-work-shifts/{id}")
    public ResponseEntity<Void> deleteOperatorWorkShift(@PathVariable Long id) {
        log.debug("REST request to delete OperatorWorkShift : {}", id);
        operatorWorkShiftService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
