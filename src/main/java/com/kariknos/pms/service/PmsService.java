package com.kariknos.pms.service;

import java.util.List;
import java.util.Optional;

import com.kariknos.pms.entity.Patient;
import com.kariknos.pms.exception.RecordNotFoundException;

public interface PmsService {
	
	/**
	 * Fetch all patient details
	 * @return
	 */
	public Optional<List<Patient>> getAllPatients();
	
	/**
	 * Add/Update patient details
	 * @param patient
	 */
	public void upsertPatient(Patient patient);
	
	/**
	 * Delete patient details
	 * @param id
	 * @throws RecordNotFoundException
	 */
	public void deletePatient(Long id) throws RecordNotFoundException;

}
