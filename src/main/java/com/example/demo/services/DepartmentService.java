package com.example.demo.services;

import com.example.demo.entity.Department;
import com.example.demo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
//    private final DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentRepository repository;

    public List<Department> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Department createDepartment(String name, boolean active) {
        Department dept = new Department();
        dept.setDepartmentName(name);
        dept.setActive(active);
        return repository.save(dept);  // Save to database
    }

    public Department updateDepartment(Long id, Department updatedData) {
        Department existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        existing.setDepartmentName(updatedData.getDepartmentName());
        existing.setActive(updatedData.isActive());
        return repository.save(existing);
    }

}

