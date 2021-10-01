package com.minhtuan.commercemanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OrderDTO {
    private String orderId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date dateOfOrder;
    private String firstnameOfReveiver;
    private String lastnameOfReveiver;
    private String addressOfReceiver;
    private String phoneOfReceiver;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date deliveryDate;
    private Integer status;
    private EmployeeDTO employee;
    private EmployeeDTO employeeDelivery;
    private Integer payment;
}
