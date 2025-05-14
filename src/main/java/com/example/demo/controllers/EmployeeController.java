package com.example.demo.controllers;

import com.example.demo.entity.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<Employee> createOrUpdate(@RequestBody Employee employee) {
        return ResponseEntity.ok(service.saveOrUpdate(employee));
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedData) {
        Employee updated = service.updateEmployee(id, updatedData);
        return ResponseEntity.ok(updated);
    }
}

