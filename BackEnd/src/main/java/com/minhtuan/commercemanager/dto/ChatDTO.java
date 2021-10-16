package com.minhtuan.commercemanager.dto;

import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ChatDTO {
    private Integer chatId;
    private String message;
    private Date time;
    private CustomerDTO customerChat;
    private EmployeeDTO employeeChat;
    private CustomerDTO customerToChat;
}
