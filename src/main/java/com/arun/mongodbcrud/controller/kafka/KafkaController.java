package com.arun.mongodbcrud.controller.kafka;

import com.arun.mongodbcrud.model.Employee;
import com.arun.mongodbcrud.service.kafka.KafkaConsumerService;
import com.arun.mongodbcrud.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/send")
    public String send(@RequestParam String message) {

        kafkaProducerService.sendMessage(message);

        return "Message Sent";
    }
    @PostMapping("employee-create")
    public String createEmployee(@RequestBody Employee employee) {

        kafkaProducerService.sendEmployee(employee);

        return "Employee Published";
    }
}