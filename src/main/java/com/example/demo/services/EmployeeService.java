package com.example.demo.services;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public Employee saveOrUpdate(Employee emp) {
        return repository.save(emp);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Employee updateEmployee(Long id, Employee updatedData) {
        Employee existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setActive(updatedData.isActive());

        // If department is being updated
        if (updatedData.getDepartment() != null && updatedData.getDepartment().getId() != null) {
            Department newDept = repository.findById(updatedData.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + updatedData.getDepartment().getId())).getDepartment();
            existing.setDepartment(newDept);
        }

        return repository.save(existing);
    }
}

