package com.example.baitapsession09.service;

import com.example.baitapsession09.dto.EmployeeCreateDTO;
import com.example.baitapsession09.model.Department;
import com.example.baitapsession09.model.Employee;
import com.example.baitapsession09.repository.DepartmentRepository;
import com.example.baitapsession09.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public Employee createEmployee(EmployeeCreateDTO dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new NoSuchElementException("Phòng ban không tồn tại"));
        Employee employee = new Employee();
        employee.setFullName(dto.getFullName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }
}
