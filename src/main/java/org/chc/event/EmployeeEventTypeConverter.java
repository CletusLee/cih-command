package org.chc.event;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class EmployeeEventTypeConverter implements DynamoDBTypeConverter<String, EmployeeEventType> {
    @Override
    public String convert(EmployeeEventType employeeEventType) {
        return employeeEventType.toString();
    }

    @Override
    public EmployeeEventType unconvert(String s) {
        return EmployeeEventType.valueOf(s);
    }
}
