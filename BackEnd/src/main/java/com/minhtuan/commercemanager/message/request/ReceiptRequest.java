package com.minhtuan.commercemanager.message.request;

import com.minhtuan.commercemanager.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReceiptRequest {
    private Double amount;
    private String taxId;
    private String orderId;
}
