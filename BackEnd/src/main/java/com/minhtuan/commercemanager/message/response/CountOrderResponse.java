package com.minhtuan.commercemanager.message.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountOrderResponse {
    private String month;
    private Long count;
}
