package com.kariknos.pms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kariknos.pms.entity.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long>{

}
