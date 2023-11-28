package com.cg.employeeservice.service;

import com.cg.employeeservice.dto.APIResponseDto;
import com.cg.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	
	APIResponseDto getEmployeeById(Long id);
}
