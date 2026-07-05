package com.arun.mongodbcrud.repository;

import com.arun.mongodbcrud.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
