package com.example.demo.controllers;

import com.example.demo.entity.Department;
import com.example.demo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping
    public List<Department> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PostMapping("/create")//
    public ResponseEntity<Department> create(@RequestBody Department department) {
        Department createdDept = service.createDepartment(department.getDepartmentName(), department.isActive());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDept);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        Department updated = service.updateDepartment(id, updatedDepartment);
        return ResponseEntity.ok(updated); // returns 200 OK with updated body
    }



}


