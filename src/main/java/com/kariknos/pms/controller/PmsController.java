package com.kariknos.pms.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kariknos.pms.entity.Patient;
import com.kariknos.pms.exception.RecordNotFoundException;
import com.kariknos.pms.service.PmsService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class PmsController {
	
	@Autowired
	PmsService pmsService;
	
	private static final String INDEX = "index";
	
	/**
	 * Method to fetch all Patient details
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping
	public String getAllPatients(Model model) {
		Optional<List<Patient>> patientList = pmsService.getAllPatients();
		if(patientList.isPresent()) {
			model.addAttribute("patientList", patientList.get());	
		}			
		return INDEX;
	}
	
	/**
	 * Method to Add/Update Patient details
	 * 
	 * @param model
	 * @param patient
	 * @return
	 */
	@PostMapping("/upsertPatient")
	public String createOrUpdateEmployee(Model model,@RequestBody Patient patient){
		pmsService.upsertPatient(patient);
		return INDEX;
	}
	
	/**
	 * Method to delete a Patient details
	 * 
	 * @param model
	 * @param id
	 * @return
	 * @throws RecordNotFoundException
	 */
	@DeleteMapping("/delete/{id}")
	public String deletePatient(Model model,@PathVariable("id") Long id)
			throws RecordNotFoundException {
		pmsService.deletePatient(id);
		return INDEX;	
	}
}
