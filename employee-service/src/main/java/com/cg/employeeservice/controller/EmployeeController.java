package com.cg.employeeservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.employeeservice.dto.APIResponseDto;
import com.cg.employeeservice.dto.EmployeeDto;
import com.cg.employeeservice.service.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
	
	private EmployeeService service;
	
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto employee = service.saveEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(employee, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable Long id){
		APIResponseDto employee = service.getEmployeeById(id);
		return new ResponseEntity<APIResponseDto>(employee, HttpStatus.OK);
	}

}
