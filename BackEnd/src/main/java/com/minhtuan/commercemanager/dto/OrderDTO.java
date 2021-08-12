package com.minhtuan.commercemanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OrderDTO {
    private String orderId;
    private Date dateOfOrder;
    private String firstnameOfReveiver;
    private String lastnameOfReveiver;
    private String addressOfReceiver;
    private String phoneOfReceiver;
    private Date deliveryDate;
    private Integer status;
}
