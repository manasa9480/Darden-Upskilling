package com.cg.employeeservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.cg.employeeservice.dto.APIResponseDto;
import com.cg.employeeservice.dto.DepartmentDto;
import com.cg.employeeservice.dto.EmployeeDto;

import com.cg.employeeservice.entity.Employee;
import com.cg.employeeservice.repository.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository repository;
	
	private ModelMapper mapper;
	
	//private RestTemplate restTemplate;
	
	private WebClient webClient;
		
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = mapper.map(employeeDto, Employee.class);
		var savedEmployee = repository.save(employee);
		return mapper.map(savedEmployee, EmployeeDto.class);
	}
	
	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	@Override
	public APIResponseDto getEmployeeById(Long id) {
		
		Employee employee = repository.findById(id).get();
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
	   //ResponseEntity<DepartmentDto> response =  restTemplate.getForEntity("http://localhost:8081/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
		
		DepartmentDto departmentDto = webClient.get()
		.uri("http://localhost:8081/api/departments/" + employee.getDepartmentCode())
		.retrieve()
		.bodyToMono(DepartmentDto.class)
		.block();
		
		
		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setDepartmentDto(departmentDto);
		apiResponseDto.setEmployeeDto(employeeDto);
		
		return apiResponseDto;
	}
	
	public APIResponseDto getDefaultDepartment(Long id, Exception exception) {
		Employee employee = repository.findById(id).get();
		EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
	   
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("R&D Department");
		departmentDto.setDepartmentDescription("Research & Development department");
		departmentDto.setDepartmentCode("RD001");
		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setDepartmentDto(departmentDto);
		apiResponseDto.setEmployeeDto(employeeDto);
		return apiResponseDto;
	}

	
}
