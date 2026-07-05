package com.arun.mongodbcrud.service.kafka;

import com.arun.mongodbcrud.model.Employee;
import com.arun.mongodbcrud.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {
    private final EmployeeRepository employeeRepository;

    public KafkaConsumerService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    @KafkaListener(topics = "employee-topic", groupId = "employee-group")
//    public String consume(String message) {
//        log.info("Received Message : " + message);
//        return "Message Received: " + message;
//    }


    @KafkaListener(topics = "employee-topic", groupId = "employee-group")
    public void consumeAndSave(Employee employee) {
        log.info("Received Employee : {}", employee);
        employeeRepository.save(employee);
    }
}