package com.minhtuan.commercemanager.message.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderResponse {
    private String orderId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfOrder;
    private String firstnameOfReveiver;
    private String lastnameOfReveiver;
    private String addressOfReceiver;
    private String phoneOfReceiver;
    private Integer status;
}
