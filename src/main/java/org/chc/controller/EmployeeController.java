package org.chc.controller;

import org.chc.command.EmployeeCommand;
import org.chc.event.EmployeeEvent;
import org.chc.event.EmployeeEventType;
import org.chc.util.TimestampUtils;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController()
@EnableWebMvc
@RequestMapping("/employee")
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private static String employeeQueryUrl = System.getenv("employeeQueryUrl");

    private static DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().build());

    @GetMapping
    public String get(){
        return "Hi!! New3!!!";
    }

    @PostMapping
    public ResponseEntity<EmployeeEvent> addEmployee(@RequestBody EmployeeCommand employee) {
        logger.info("Ready to add a new employee!!");
        logger.info("Query component URL: " + employeeQueryUrl);
        employee.setTime(TimestampUtils.getTimeInIso8601());
        EmployeeEvent employeeEvent = new EmployeeEvent(employee, EmployeeEventType.AddedNewEmployeeEvent);
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = employeeQueryUrl + "/employee/name/" + employeeEvent.getName();
            logger.info("query url: " + url);
            String result = restTemplate.getForObject(url, String.class);
            logger.info("queried result: " + result);
            return new ResponseEntity("Can't add an employee with the same that has been added.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.info("Ready to save the event.");
            mapper.save(employeeEvent);
            return new ResponseEntity<>(employeeEvent, HttpStatus.ACCEPTED);
        }

    }
}
