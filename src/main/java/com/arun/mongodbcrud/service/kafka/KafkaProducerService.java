package com.arun.mongodbcrud.service.kafka;

import com.arun.mongodbcrud.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Employee> kafkaTemplates;

    public void sendMessage(String message) {

        kafkaTemplate.send("employee-topic", message);

        System.out.println("Message Sent : " + message);
    }

    public void sendEmployee(Employee employee) {

        kafkaTemplates.send("employee-topic", employee);
    }
}