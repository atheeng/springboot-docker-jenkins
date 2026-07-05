package com.arun.mongodbcrud.controller;

import com.arun.mongodbcrud.model.Employee;
import com.arun.mongodbcrud.service.EmployeeService;
import com.arun.mongodbcrud.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final KafkaProducerService kafkaProducerService;

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }



    @GetMapping
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable String id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        employeeService.delete(id);
        return "Employee deleted successfully";
    }
}
