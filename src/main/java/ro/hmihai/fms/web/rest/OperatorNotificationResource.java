package ro.hmihai.fms.web.rest;

import ro.hmihai.fms.service.OperatorNotificationService;
import ro.hmihai.fms.web.rest.errors.BadRequestAlertException;
import ro.hmihai.fms.service.dto.OperatorNotificationDTO;

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
 * REST controller for managing {@link ro.hmihai.fms.domain.OperatorNotification}.
 */
@RestController
@RequestMapping("/api")
public class OperatorNotificationResource {

    private final Logger log = LoggerFactory.getLogger(OperatorNotificationResource.class);

    private static final String ENTITY_NAME = "operatorNotification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperatorNotificationService operatorNotificationService;

    public OperatorNotificationResource(OperatorNotificationService operatorNotificationService) {
        this.operatorNotificationService = operatorNotificationService;
    }

    /**
     * {@code POST  /operator-notifications} : Create a new operatorNotification.
     *
     * @param operatorNotificationDTO the operatorNotificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operatorNotificationDTO, or with status {@code 400 (Bad Request)} if the operatorNotification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operator-notifications")
    public ResponseEntity<OperatorNotificationDTO> createOperatorNotification(@RequestBody OperatorNotificationDTO operatorNotificationDTO) throws URISyntaxException {
        log.debug("REST request to save OperatorNotification : {}", operatorNotificationDTO);
        if (operatorNotificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new operatorNotification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperatorNotificationDTO result = operatorNotificationService.save(operatorNotificationDTO);
        return ResponseEntity.created(new URI("/api/operator-notifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operator-notifications} : Updates an existing operatorNotification.
     *
     * @param operatorNotificationDTO the operatorNotificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operatorNotificationDTO,
     * or with status {@code 400 (Bad Request)} if the operatorNotificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operatorNotificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operator-notifications")
    public ResponseEntity<OperatorNotificationDTO> updateOperatorNotification(@RequestBody OperatorNotificationDTO operatorNotificationDTO) throws URISyntaxException {
        log.debug("REST request to update OperatorNotification : {}", operatorNotificationDTO);
        if (operatorNotificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OperatorNotificationDTO result = operatorNotificationService.save(operatorNotificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operatorNotificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /operator-notifications} : get all the operatorNotifications.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operatorNotifications in body.
     */
    @GetMapping("/operator-notifications")
    public ResponseEntity<List<OperatorNotificationDTO>> getAllOperatorNotifications(Pageable pageable) {
        log.debug("REST request to get a page of OperatorNotifications");
        Page<OperatorNotificationDTO> page = operatorNotificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /operator-notifications/:id} : get the "id" operatorNotification.
     *
     * @param id the id of the operatorNotificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operatorNotificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operator-notifications/{id}")
    public ResponseEntity<OperatorNotificationDTO> getOperatorNotification(@PathVariable Long id) {
        log.debug("REST request to get OperatorNotification : {}", id);
        Optional<OperatorNotificationDTO> operatorNotificationDTO = operatorNotificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operatorNotificationDTO);
    }

    /**
     * {@code DELETE  /operator-notifications/:id} : delete the "id" operatorNotification.
     *
     * @param id the id of the operatorNotificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operator-notifications/{id}")
    public ResponseEntity<Void> deleteOperatorNotification(@PathVariable Long id) {
        log.debug("REST request to delete OperatorNotification : {}", id);
        operatorNotificationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
