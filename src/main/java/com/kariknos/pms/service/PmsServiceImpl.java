package com.kariknos.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kariknos.pms.entity.Patient;
import com.kariknos.pms.exception.RecordNotFoundException;
import com.kariknos.pms.repository.PatientRepository;

@Service
public class PmsServiceImpl implements PmsService {

	@Autowired
	PatientRepository patientRepository;

	@Override
	public Optional<List<Patient>> getAllPatients() {
		List<Patient> patientList = (List<Patient>) patientRepository.findAll();
		if (ObjectUtils.isEmpty(patientList)) {
			return Optional.empty();
		}
		return Optional.of(patientList);
	}

	@Override
	public void upsertPatient(Patient patient) {
		if (patient.getId() == null) {
			patientRepository.save(patient);
		} else {
			Optional<Patient> patientEntity = patientRepository.findById(patient.getId());
			if (patientEntity.isPresent()) {
				Patient updatePatient = patientEntity.get();
				updatePatient.setName(patient.getName());
				updatePatient.setGender(patient.getGender());
				updatePatient.setPhoneNumber(patient.getPhoneNumber());
				patientRepository.save(updatePatient);
			} else {
				patientRepository.save(patient);
			}

		}
	}

	@Override
	public void deletePatient(Long id) throws RecordNotFoundException {
		Optional<Patient> patientEntity = patientRepository.findById(id);
		if (patientEntity.isPresent()) {
			patientRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No patient record exist for given id");
		}
	}

}
