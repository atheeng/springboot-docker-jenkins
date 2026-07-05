package com.arun.mongodbcrud.service;

import com.arun.mongodbcrud.model.Employee;
import com.arun.mongodbcrud.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @CacheEvict(value = "employees", allEntries = true)
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Cacheable(value = "employee", key = "#id")//First call goes to MongoDB, second call comes from Redis.
    public Employee findById(String id) {
        System.out.println("Fetching employee from MongoDB...");
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    /**
     * @Cacheable = Read from cache
     * @CachePut = Update cache
     * @CacheEvict = Remove from cache
     * @param id
     * @param request
     * @return
     */
    public Employee update(String id, Employee request) {
        Employee employee = findById(id);
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = "employee", key = "#id")
    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}