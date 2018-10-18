package com.amazonaws.serverless.sample.springboot.event;

import com.amazonaws.serverless.sample.springboot.command.EmployeeCommand;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.UUID;

@DynamoDBTable(tableName = "employee")
public class EmployeeEvent {
    private String event = "employee event";
    private String time;
    private String id;
    private String name;
    private String gender;
    private String team;
    private Integer age;
    private Integer height;
    private EmployeeEventType employeeEventType;

    public EmployeeEvent(EmployeeCommand employee, EmployeeEventType employeeEventType) {
        this.time = employee.getTime();
        this.id = UUID.randomUUID().toString();
        this.name = employee.getName();
        this.gender = employee.getGender().toString();
        this.team = employee.getTeam().toString();
        this.age = employee.getAge();
        this.height = employee.getHeight();
        this.employeeEventType = employeeEventType;
    }

    @DynamoDBHashKey(attributeName = "event")
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @DynamoDBRangeKey(attributeName = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @DynamoDBAttribute(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBTypeConverted(converter = EmployeeEventTypeConverter.class)
    @DynamoDBAttribute(attributeName = "employeeEventType")
    public EmployeeEventType getEmployeeEventType() {
        return employeeEventType;
    }

    public void setEmployeeEventType(EmployeeEventType employeeEventType) {
        this.employeeEventType = employeeEventType;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @DynamoDBAttribute(attributeName = "team")
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @DynamoDBAttribute(attributeName = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @DynamoDBAttribute(attributeName = "height")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
