package com.minhtuan.commercemanager.message.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatisticResponse {
    private String name;
    private Long value;
    private String text;
}
