package com.minhtuan.commercemanager.message.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class OrderResponse {
    private String orderId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date dateOfOrder;
    private String firstnameOfReveiver;
    private String lastnameOfReveiver;
    private String addressOfReceiver;
    private String phoneOfReceiver;
    private Integer status;
    private Integer payment;
}
