package com.minhtuan.commercemanager.message.request;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class OrderRequest {
    private String firstnameOfReveiver;
    private String lastnameOfReveiver;
    private String addressOfReceiver;
    private String phoneOfReceiver;
    private String customerId;
}
