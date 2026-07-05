package com.arun.mongodbcrud.service;

import com.arun.mongodbcrud.model.Employee;
import com.arun.mongodbcrud.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee1 = new Employee("1", "Arun", "arun@gmail.com", 50000.0);
        employee2 = new Employee("2", "Ram", "ram@gmail.com", 60000.0);
    }

    @Test
    void testSaveEmployee() {
        when(employeeRepository.save(employee1)).thenReturn(employee1);
        Employee result = employeeService.save(employee1);
        assertEquals("1", result.getId());
        assertEquals("Arun", result.getName());
        assertEquals("arun@gmail.com", result.getEmail());
        assertEquals(50000, result.getSalary());

        verify(employeeRepository, times(1)).save(employee1);
    }
    @Test
    void testGetAllEmployees() {

        List<Employee> employees = List.of(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAll();

        assertEquals(2, result.size());
        assertEquals("Arun", result.get(0).getName());
        assertEquals("Ram", result.get(1).getName());

        verify(employeeRepository, times(1)).findAll();
    }
    @AfterAll
    static void cleanup() {
        System.out.println("End Test Suite");
    }
    @BeforeAll
    static void init() {
        System.out.println("Start Test Suite");
    }

}
//        Employee employee = new Employee();
//        employee.setId("1");
//        employee.setName("Arun");
//        employee.setEmail("arun@gmail.com");
//        employee.setSalary(Double.valueOf(50000));
/**
 * @Mock creates fake repository.
 * @InjectMocks injects fake repository into service.
 * <p>
 * when(...).thenReturn(...) defines expected fake response.
 * <p>
 * assertEquals(...) checks result.
 * <p>
 * verify(...) checks repository method called one time.
 */