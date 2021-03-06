package com.gr.ntua.service.impl;

import com.gr.ntua.service.PatientService;
import com.gr.ntua.domain.Patient;
import com.gr.ntua.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Patient.
 */
@Service
@Transactional
public class PatientServiceImpl implements PatientService{

    private final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Inject
    private PatientRepository patientRepository;

    /**
     * Save a patient.
     *
     * @param patient the entity to save
     * @return the persisted entity
     */
    public Patient save(Patient patient) {
        log.debug("Request to save Patient : {}", patient);
        Patient result = patientRepository.save(patient);
        return result;
    }

    /**
     *  Get all the patients.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Patient> findAll(Pageable pageable) {
        log.debug("Request to get all Patients");
        Page<Patient> result = patientRepository.findByUserIsCurrentUser(pageable);
        return result;
    }

    /**
     *  Get one patient by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Patient findOne(Long id) {
        log.debug("Request to get Patient : {}", id);
        Patient patient = patientRepository.findOneWhereUserIsCurrentUser(id);
        return patient;
    }

    /**
     *  Delete the  patient by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Patient : {}", id);
        patientRepository.delete(id);
    }
}
