package com.cg.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
