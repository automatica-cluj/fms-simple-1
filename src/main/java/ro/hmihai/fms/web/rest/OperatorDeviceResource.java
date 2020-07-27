package ro.hmihai.fms.web.rest;

import ro.hmihai.fms.service.OperatorDeviceService;
import ro.hmihai.fms.web.rest.errors.BadRequestAlertException;
import ro.hmihai.fms.service.dto.OperatorDeviceDTO;

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
 * REST controller for managing {@link ro.hmihai.fms.domain.OperatorDevice}.
 */
@RestController
@RequestMapping("/api")
public class OperatorDeviceResource {

    private final Logger log = LoggerFactory.getLogger(OperatorDeviceResource.class);

    private static final String ENTITY_NAME = "operatorDevice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperatorDeviceService operatorDeviceService;

    public OperatorDeviceResource(OperatorDeviceService operatorDeviceService) {
        this.operatorDeviceService = operatorDeviceService;
    }

    /**
     * {@code POST  /operator-devices} : Create a new operatorDevice.
     *
     * @param operatorDeviceDTO the operatorDeviceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operatorDeviceDTO, or with status {@code 400 (Bad Request)} if the operatorDevice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operator-devices")
    public ResponseEntity<OperatorDeviceDTO> createOperatorDevice(@RequestBody OperatorDeviceDTO operatorDeviceDTO) throws URISyntaxException {
        log.debug("REST request to save OperatorDevice : {}", operatorDeviceDTO);
        if (operatorDeviceDTO.getId() != null) {
            throw new BadRequestAlertException("A new operatorDevice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperatorDeviceDTO result = operatorDeviceService.save(operatorDeviceDTO);
        return ResponseEntity.created(new URI("/api/operator-devices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operator-devices} : Updates an existing operatorDevice.
     *
     * @param operatorDeviceDTO the operatorDeviceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operatorDeviceDTO,
     * or with status {@code 400 (Bad Request)} if the operatorDeviceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operatorDeviceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operator-devices")
    public ResponseEntity<OperatorDeviceDTO> updateOperatorDevice(@RequestBody OperatorDeviceDTO operatorDeviceDTO) throws URISyntaxException {
        log.debug("REST request to update OperatorDevice : {}", operatorDeviceDTO);
        if (operatorDeviceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OperatorDeviceDTO result = operatorDeviceService.save(operatorDeviceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operatorDeviceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /operator-devices} : get all the operatorDevices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operatorDevices in body.
     */
    @GetMapping("/operator-devices")
    public ResponseEntity<List<OperatorDeviceDTO>> getAllOperatorDevices(Pageable pageable) {
        log.debug("REST request to get a page of OperatorDevices");
        Page<OperatorDeviceDTO> page = operatorDeviceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /operator-devices/:id} : get the "id" operatorDevice.
     *
     * @param id the id of the operatorDeviceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operatorDeviceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operator-devices/{id}")
    public ResponseEntity<OperatorDeviceDTO> getOperatorDevice(@PathVariable Long id) {
        log.debug("REST request to get OperatorDevice : {}", id);
        Optional<OperatorDeviceDTO> operatorDeviceDTO = operatorDeviceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operatorDeviceDTO);
    }

    /**
     * {@code DELETE  /operator-devices/:id} : delete the "id" operatorDevice.
     *
     * @param id the id of the operatorDeviceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operator-devices/{id}")
    public ResponseEntity<Void> deleteOperatorDevice(@PathVariable Long id) {
        log.debug("REST request to delete OperatorDevice : {}", id);
        operatorDeviceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
