package ro.hmihai.fms.web.rest;

import ro.hmihai.fms.service.FactoryEventService;
import ro.hmihai.fms.web.rest.errors.BadRequestAlertException;
import ro.hmihai.fms.service.dto.FactoryEventDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ro.hmihai.fms.domain.FactoryEvent}.
 */
@RestController
@RequestMapping("/api")
public class FactoryEventResource {

    private final Logger log = LoggerFactory.getLogger(FactoryEventResource.class);

    private static final String ENTITY_NAME = "factoryEvent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FactoryEventService factoryEventService;

    public FactoryEventResource(FactoryEventService factoryEventService) {
        this.factoryEventService = factoryEventService;
    }

    /**
     * {@code POST  /factory-events} : Create a new factoryEvent.
     *
     * @param factoryEventDTO the factoryEventDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new factoryEventDTO, or with status {@code 400 (Bad Request)} if the factoryEvent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/factory-events")
    public ResponseEntity<FactoryEventDTO> createFactoryEvent(@Valid @RequestBody FactoryEventDTO factoryEventDTO) throws URISyntaxException {
        log.debug("REST request to save FactoryEvent : {}", factoryEventDTO);
        if (factoryEventDTO.getId() != null) {
            throw new BadRequestAlertException("A new factoryEvent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FactoryEventDTO result = factoryEventService.save(factoryEventDTO);
        return ResponseEntity.created(new URI("/api/factory-events/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /factory-events} : Updates an existing factoryEvent.
     *
     * @param factoryEventDTO the factoryEventDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated factoryEventDTO,
     * or with status {@code 400 (Bad Request)} if the factoryEventDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the factoryEventDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/factory-events")
    public ResponseEntity<FactoryEventDTO> updateFactoryEvent(@Valid @RequestBody FactoryEventDTO factoryEventDTO) throws URISyntaxException {
        log.debug("REST request to update FactoryEvent : {}", factoryEventDTO);
        if (factoryEventDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FactoryEventDTO result = factoryEventService.save(factoryEventDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, factoryEventDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /factory-events} : get all the factoryEvents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of factoryEvents in body.
     */
    @GetMapping("/factory-events")
    public ResponseEntity<List<FactoryEventDTO>> getAllFactoryEvents(Pageable pageable) {
        log.debug("REST request to get a page of FactoryEvents");
        Page<FactoryEventDTO> page = factoryEventService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /factory-events/:id} : get the "id" factoryEvent.
     *
     * @param id the id of the factoryEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the factoryEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/factory-events/{id}")
    public ResponseEntity<FactoryEventDTO> getFactoryEvent(@PathVariable Long id) {
        log.debug("REST request to get FactoryEvent : {}", id);
        Optional<FactoryEventDTO> factoryEventDTO = factoryEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(factoryEventDTO);
    }

    /**
     * {@code DELETE  /factory-events/:id} : delete the "id" factoryEvent.
     *
     * @param id the id of the factoryEventDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/factory-events/{id}")
    public ResponseEntity<Void> deleteFactoryEvent(@PathVariable Long id) {
        log.debug("REST request to delete FactoryEvent : {}", id);
        factoryEventService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
