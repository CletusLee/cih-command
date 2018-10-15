package com.amazonaws.serverless.sample.springboot.controller;

import com.amazonaws.serverless.sample.springboot.command.CommandResponse;
import com.amazonaws.serverless.sample.springboot.command.EmployeeCommand;
import com.amazonaws.serverless.sample.springboot.event.EmployeeEvent;
import com.amazonaws.serverless.sample.springboot.event.EmployeeEventType;
import com.amazonaws.serverless.sample.springboot.util.TimestampUtils;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.UUID;

@RestController()
@EnableWebMvc
@RequestMapping("/employee")
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private static DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().build());

    @GetMapping
    public String get(){
        return "Hi!! New3!!!";
    }

    @PostMapping
    public EmployeeEvent addEmployee(@RequestBody EmployeeCommand employee) {
        logger.info("Ready to add a new employee!!");
        employee.setTime(TimestampUtils.getTimeInIso8601());
        EmployeeEvent employeeEvent = new EmployeeEvent(employee, EmployeeEventType.AddedNewEmployeeEvent);
        mapper.save(employeeEvent);

        return employeeEvent;
    }
}
