package com.team7.leave.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team7.leave.Repositories.PublicHolidayRepository;
import com.team7.leave.model.PublicHoliday;
import com.team7.leave.services.PublicHolidayService;

@CrossOrigin(origins= "http://localhost:3000/")
@RestController
@RequestMapping("/api/publicholiday")
public class PublicHolidayController {

	@Autowired
	private PublicHolidayService phService;
	@Autowired
	private PublicHolidayRepository phRepository;

	// create public holiday REST API
	@PostMapping()
	public ResponseEntity<PublicHoliday> savePublicHoliday(@RequestBody PublicHoliday publicHoliday) {
		return new ResponseEntity<PublicHoliday>(phService.savePublicHoliday(publicHoliday), HttpStatus.CREATED);
	}

	// get all public holiday REST API
	@GetMapping()
	public List<PublicHoliday> getAllPublicHoliday() {
		return phService.getAllPublicHoliday();
	}

	// get public holiday by id REST API
	@GetMapping("{id}")
	public ResponseEntity<PublicHoliday> getPublicHolidayById(@PathVariable("id") @DateTimeFormat(iso = ISO.DATE) LocalDate id) {
		Optional<PublicHoliday> phData = phService.getPublicHolidayById(id);

		if (phData.isPresent()) {
			return new ResponseEntity<>(phData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// update public holiday REST API
	@PutMapping("{id}")
	public ResponseEntity<PublicHoliday> updatePublicHoliday(@PathVariable("id") @DateTimeFormat(iso = ISO.DATE) LocalDate id, 
			                                                 @RequestBody PublicHoliday publicHoliday){
		Optional<PublicHoliday> phData = phService.getPublicHolidayById(id);

		if (phData.isPresent()) {
			PublicHoliday existingPublicHoliday = phData.get();
			existingPublicHoliday.setDateFrom(publicHoliday.getDateFrom());
			existingPublicHoliday.setName(publicHoliday.getName());
			return new ResponseEntity<>(phRepository.save(existingPublicHoliday), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// delete public holiday by id REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePublicHoliday(@PathVariable("id") @DateTimeFormat(iso = ISO.DATE) LocalDate id){
		try {
			phRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	// delete all public holiday REST API
	@DeleteMapping()
	public ResponseEntity<String> deleteAllPublicHoliday(){
		try {
			phRepository.deleteAll();;
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
