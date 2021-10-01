package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.message.response.CountOrderResponse;
import com.minhtuan.commercemanager.message.response.StatisticResponse;

import java.text.ParseException;
import java.util.List;

public interface StatisticService {
    List<StatisticResponse> getSaleStatistic(String fromDate, String toDate) throws ParseException;
    List<CountOrderResponse> getCountOrderMonthly() throws ParseException;
    List<CountOrderResponse> getCountAccountMonthly() throws ParseException;
}
