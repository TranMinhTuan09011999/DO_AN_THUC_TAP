package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.HotSellingProductDTO;
import com.minhtuan.commercemanager.message.response.CountOrderResponse;
import com.minhtuan.commercemanager.message.response.StatisticResponse;

import java.text.ParseException;
import java.util.List;

public interface StatisticService {
    List<CountOrderResponse> getCountOrderMonthly() throws ParseException;
    List<CountOrderResponse> getCountAccountMonthly() throws ParseException;
    List<HotSellingProductDTO> getHotSellingProduct(String date);
    List<HotSellingProductDTO> getHotSellingProductByMonthFromTo(String fromDate, String toDate) throws ParseException;
    List<HotSellingProductDTO> getHotSellingProductByQuater(Integer quater, Integer year, Integer quater1, Integer year1) throws ParseException;
    List<HotSellingProductDTO> getHotSellingProductByYear(Integer year, Integer year1) throws ParseException;
    List<CountOrderResponse> getCountAccountByMonth(String fromDate, String toDate) throws ParseException;
    List<CountOrderResponse> getCountAccountByQuater(Integer quater, Integer year, Integer quater1, Integer year1) throws ParseException;
    List<CountOrderResponse> getCountAccountByYear(Integer year, Integer year1) throws ParseException;
    List<StatisticResponse> getStaticByMonth(String fromDate, String toDate) throws ParseException;
    List<StatisticResponse> getStaticByQuater(Integer quater, Integer year, Integer quater1, Integer year1) throws ParseException;
    List<StatisticResponse> getStaticByYear(Integer year, Integer year1) throws ParseException;

    //Tính lợi nhuận
    List<CountOrderResponse> getProfitByMonth(String fromDate, String toDate) throws ParseException;
    List<CountOrderResponse> getProfitByQuater(Integer quater, Integer year, Integer quater1, Integer year1) throws ParseException;
    List<CountOrderResponse> getProfitByYear(Integer year, Integer year1) throws ParseException;
}
