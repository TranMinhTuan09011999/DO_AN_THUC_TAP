package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ColorDTO;
import com.minhtuan.commercemanager.dto.HotSellingProductDTO;
import com.minhtuan.commercemanager.dto.InvoiceDTO;
import com.minhtuan.commercemanager.dto.OrderDTO;
import com.minhtuan.commercemanager.maper.InvoiceMapper;
import com.minhtuan.commercemanager.maper.OrderMapper;
import com.minhtuan.commercemanager.message.response.*;
import com.minhtuan.commercemanager.model.Account;
import com.minhtuan.commercemanager.model.Invoice;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.repository.AccountRepository;
import com.minhtuan.commercemanager.repository.InvoiceRepository;
import com.minhtuan.commercemanager.repository.OrderRepository;
import com.minhtuan.commercemanager.repository.WarehouseReceiptRepository;
import com.minhtuan.commercemanager.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private WarehouseReceiptRepository warehouseReceiptRepository;

    @Override
    public List<CountOrderResponse> getCountOrderMonthly() throws ParseException {
        long millis=System.currentTimeMillis();
        Date today = new java.sql.Date(millis);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = formatter.format(today);
        String[] str = dateFormat.split("-");
        String year = str[0].toString();
        String month = str[1].toString();
        Integer yearInt= Integer.parseInt(year);
        Integer monthInt = Integer.parseInt(month);

        List<CountOrderResponse> list = new ArrayList<>();

        for(int i=1; i<=monthInt; i++){
            CountOrderResponse countOrderResponse = new CountOrderResponse();
            Date date_From1 = new Date();
            Date date_To1 = new Date();
            String month1 = "";
            Integer k = 0;
            if(i == 1){
                month1 = "Jan";
                date_From1 = formatter.parse(year + "-01-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-01-31");
                }
            }
            if(i == 2){
                month1 = "Feb";
                date_From1 = formatter.parse(year + "-02-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-02-29");
                }
            }
            if(i == 3){
                month1 = "Mar";
                date_From1 = formatter.parse(year + "-03-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-03-31");
                }
            }
            if(i == 4){
                month1 = "Apr";
                date_From1 = formatter.parse(year + "-04-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-04-30");
                }
            }
            if(i == 5){
                month1 = "May";
                date_From1 = formatter.parse(year + "-05-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-05-31");
                }
            }
            if(i == 6){
                month1 = "Jun";
                date_From1 = formatter.parse(year + "-06-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-06-30");
                }
            }
            if(i == 7){
                month1 = "Jul";
                date_From1 = formatter.parse(year + "-07-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-07-31");
                }
            }
            if(i == 8){
                month1 = "Aug";
                date_From1 = formatter.parse(year + "-08-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-08-31");
                }
            }
            if(i == 9){
                month1 = "Sep";
                date_From1 = formatter.parse(year + "-09-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-09-30");
                }
            }
            if(i == 10){
                month1 = "Oct";
                date_From1 = formatter.parse(year + "-10-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-10-31");
                }
            }
            if(i == 11){
                month1 = "Nov";
                date_From1 = formatter.parse(year + "-11-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-11-30");
                }
            }
            if(i == 12){
                month1 = "Dec";
                date_From1 = formatter.parse(year + "-12-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-12-31");
                }
            }
            List<Order> orderList1 = orderRepository.findOrdersByStatusOrderByOrderIdDescAndDate(4, date_From1, date_To1);
            Long count = orderList1.stream().count();
            countOrderResponse.setMonth(month1);
            countOrderResponse.setCount(count);
            list.add(countOrderResponse);
            if(k == 1){
                break;
            }
        }
        return list;
    }

    @Override
    public List<CountOrderResponse> getCountAccountMonthly() throws ParseException {
        long millis=System.currentTimeMillis();
        Date today = new java.sql.Date(millis);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = formatter.format(today);
        String[] str = dateFormat.split("-");
        String year = str[0].toString();
        String month = str[1].toString();
        Integer yearInt= Integer.parseInt(year);
        Integer monthInt = Integer.parseInt(month);

        List<CountOrderResponse> list = new ArrayList<>();

        for(int i=1; i<=monthInt; i++){
            CountOrderResponse countOrderResponse = new CountOrderResponse();
            Date date_From1 = new Date();
            Date date_To1 = new Date();
            String month1 = "";
            Integer k = 0;
            if(i == 1){
                month1 = "Jan";
                date_From1 = formatter.parse(year + "-01-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-01-31");
                }
            }
            if(i == 2){
                month1 = "Feb";
                date_From1 = formatter.parse(year + "-02-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-02-29");
                }
            }
            if(i == 3){
                month1 = "Mar";
                date_From1 = formatter.parse(year + "-03-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-03-31");
                }
            }
            if(i == 4){
                month1 = "Apr";
                date_From1 = formatter.parse(year + "-04-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-04-30");
                }
            }
            if(i == 5){
                month1 = "May";
                date_From1 = formatter.parse(year + "-05-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-05-31");
                }
            }
            if(i == 6){
                month1 = "Jun";
                date_From1 = formatter.parse(year + "-06-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-06-30");
                }
            }
            if(i == 7){
                month1 = "Jul";
                date_From1 = formatter.parse(year + "-07-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-07-31");
                }
            }
            if(i == 8){
                month1 = "Aug";
                date_From1 = formatter.parse(year + "-08-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-08-31");
                }
            }
            if(i == 9){
                month1 = "Sep";
                date_From1 = formatter.parse(year + "-09-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-09-30");
                }
            }
            if(i == 10){
                month1 = "Oct";
                date_From1 = formatter.parse(year + "-10-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-10-31");
                }
            }
            if(i == 11){
                month1 = "Nov";
                date_From1 = formatter.parse(year + "-11-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-11-30");
                }
            }
            if(i == 12){
                month1 = "Dec";
                date_From1 = formatter.parse(year + "-12-01");
                if(i == monthInt){
                    date_To1 = today;
                    k = 1;
                }else{
                    date_To1 = formatter.parse(year + "-12-31");
                }
            }
            List<Account> accountList = accountRepository.findAccountsByStatusOrderByAndDate(2, date_From1, date_To1);
            Long count = accountList.stream().count();
            countOrderResponse.setMonth(month1);
            countOrderResponse.setCount(count);
            list.add(countOrderResponse);
            if(k == 1){
                break;
            }
        }
        return list;
    }

    @Override
    public List<HotSellingProductDTO> getHotSellingProduct(String date) {
        String[] dateArr = date.split("-");
        Integer month = Integer.parseInt(dateArr[1]);
        Long year = Long.parseLong(dateArr[0]);
        List<Object[]> list = invoiceRepository.getHotSellingProduct(month, year);
        List<HotSellingProductDTO> hotSellingProductDTOList = list.stream().map(objects -> {
            return new HotSellingProductDTO(objects);
        }).collect(Collectors.toList());
        return hotSellingProductDTOList;
    }

    @Override
    public List<HotSellingProductDTO> getHotSellingProductByMonthFromTo(String fromDate, String toDate) throws ParseException {
        String fromDate1 = fromDate + "-01";
        String[] dateArr = toDate.split("-");
        Integer month = Integer.parseInt(dateArr[1]);
        String toDate1 = "";
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 7 || month == 10 || month == 12){
            toDate1 = toDate + "-31";
        }else if(month == 2){
            toDate1 = toDate + "-29";
        }else {
            toDate1 = toDate + "-30";
        }
        System.out.println(fromDate1);
        System.out.println(toDate1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = sdf.parse(fromDate1);
        Date dateTo = sdf.parse(toDate1);
        List<Object[]> list = invoiceRepository.getHotSellingProductMonthFromTo(dateFrom, dateTo);
        List<HotSellingProductDTO> hotSellingProductDTOList = list.stream().map(objects -> {
            return new HotSellingProductDTO(objects);
        }).collect(Collectors.toList());
        return hotSellingProductDTOList;
    }

    @Override
    public List<HotSellingProductDTO> getHotSellingProductByQuater(Integer quater, Integer year, Integer quater1, Integer year1) throws ParseException {
        String dateArrFrom = null;
        String dateArrTo = null;
        if(quater1 == null && year1 == null){
            if(quater == 1){
                dateArrFrom = year.toString() + "-01-01";
                dateArrTo = year.toString() + "-03-31";
            }else if(quater == 2){
                dateArrFrom = year.toString() + "-04-01";
                dateArrTo = year.toString() + "-06-30";
            }else if(quater == 3){
                dateArrFrom = year.toString() + "-07-01";
                dateArrTo = year.toString() + "-09-30";
            }else{
                dateArrFrom = year.toString() + "-10-01";
                dateArrTo = year.toString() + "-12-31";
            }
        }else {
            if(quater == 1){
                dateArrFrom = year.toString() + "-01-01";
            }else if(quater == 2){
                dateArrFrom = year.toString() + "-04-01";
            }else if(quater == 3){
                dateArrFrom = year.toString() + "-07-01";
            }else{
                dateArrFrom = year.toString() + "-10-01";
            }

            if(quater1 == 11){
                dateArrTo = year1.toString() + "-03-31";
            }else if(quater1 == 22){
                dateArrTo = year1.toString() + "-06-30";
            }else if(quater1 == 33){
                dateArrTo = year1.toString() + "-09-30";
            }else{
                dateArrTo = year1.toString() + "-12-31";
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = sdf.parse(dateArrFrom);
        Date dateTo = sdf.parse(dateArrTo);
        List<Object[]> list = invoiceRepository.getHotSellingProductMonthFromTo(dateFrom, dateTo);
        List<HotSellingProductDTO> hotSellingProductDTOList = list.stream().map(objects -> {
            return new HotSellingProductDTO(objects);
        }).collect(Collectors.toList());
        return hotSellingProductDTOList;
    }

    @Override
    public List<HotSellingProductDTO> getHotSellingProductByYear(Integer year, Integer year1) throws ParseException {
        String dateArrFrom = null;
        String dateArrTo = null;
        if(year1 == null){
            dateArrFrom = year.toString() + "-01-01";
            dateArrTo = year.toString() + "-12-31";
        }else {
            dateArrFrom = year.toString() + "-01-01";
            dateArrTo = year1.toString() + "-12-31";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = sdf.parse(dateArrFrom);
        Date dateTo = sdf.parse(dateArrTo);
        List<Object[]> list = invoiceRepository.getHotSellingProductMonthFromTo(dateFrom, dateTo);
        List<HotSellingProductDTO> hotSellingProductDTOList = list.stream().map(objects -> {
            return new HotSellingProductDTO(objects);
        }).collect(Collectors.toList());
        return hotSellingProductDTOList;
    }

    @Override
    public List<CountOrderResponse> getCountAccountByMonth(String fromDate, String toDate) throws ParseException {
        String[] fromMonthStr = fromDate.split("-");
        String yearFrom = fromMonthStr[0].toString();
        String monthFrom = fromMonthStr[1].toString();
        //Integer yearFromInt= Integer.parseInt(yearFrom);
        Integer monthFromInt = Integer.parseInt(monthFrom);

        String[] toMonthStr = toDate.split("-");
        String yearTo = toMonthStr[0].toString();
        String monthTo = toMonthStr[1].toString();
        Integer yearToInt= Integer.parseInt(yearTo);
        Integer monthToInt = Integer.parseInt(monthTo);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<CountOrderResponse> list = new ArrayList<>();

        while (true){
            CountOrderResponse countOrderResponse = new CountOrderResponse();
            Date date_From = new Date();
            Date date_To = new Date();
            String month = "";

            if(monthFromInt == 1){
                month = "T1(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-01-01");
                date_To = formatter.parse(yearFrom + "-01-31");
            }
            if(monthFromInt == 2){
                month = "T2(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-02-01");
                date_To = formatter.parse(yearFrom + "-02-29");
            }
            if(monthFromInt == 3){
                month = "T3(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-03-01");
                date_To = formatter.parse(yearFrom + "-03-31");
            }
            if(monthFromInt == 4){
                month = "T4(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-04-01");
                date_To = formatter.parse(yearFrom + "-04-30");
            }
            if(monthFromInt == 5){
                month = "T5(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-05-01");
                date_To = formatter.parse(yearFrom + "-05-31");
            }
            if(monthFromInt == 6){
                month = "T6(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-06-01");
                date_To = formatter.parse(yearFrom + "-06-30");
            }
            if(monthFromInt == 7){
                month = "T7(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-07-01");
                date_To = formatter.parse(yearFrom + "-07-31");
            }
            if(monthFromInt == 8){
                month = "T8(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-08-01");
                date_To = formatter.parse(yearFrom + "-08-31");
            }
            if(monthFromInt == 9){
                month = "T9(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-09-01");
                date_To = formatter.parse(yearFrom + "-09-30");
            }
            if(monthFromInt == 10){
                month = "T10(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-10-01");
                date_To = formatter.parse(yearFrom + "-10-31");
            }
            if(monthFromInt == 11){
                month = "T11(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-11-01");
                date_To = formatter.parse(yearFrom + "-11-30");
            }
            if(monthFromInt == 12){
                month = "T12(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-12-01");
                date_To = formatter.parse(yearFrom + "-12-31");
            }

            List<Account> accountList = accountRepository.findAccountsByStatusOrderByAndDate(2, date_From, date_To);
            Long count = accountList.stream().count();
            countOrderResponse.setMonth(month);
            countOrderResponse.setCount(count);
            list.add(countOrderResponse);

            if(monthFromInt == monthToInt && yearFrom.equals(yearTo)){
                break;
            }

            if(monthFromInt == 12){
                Integer yearFromInt= Integer.parseInt(yearFrom);
                yearFromInt++;
                yearFrom = yearFromInt.toString();
                monthFromInt = 1;
            }else {
                monthFromInt++;
            }
        }
        return list;
    }

    @Override
    public List<CountOrderResponse> getCountAccountByQuater(Integer quater, Integer year, Integer quater1, Integer year1) throws ParseException {
        String dateArrFrom = null;
        String dateArrTo = null;
        List<CountOrderResponse> list = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        while (true){
            CountOrderResponse countOrderResponse = new CountOrderResponse();
            Date date_From = new Date();
            Date date_To = new Date();
            String quaterStr = "";

            if(quater == 1){
                quaterStr = "Q1(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-01-01");
                date_To = formatter.parse(year.toString() + "-03-31");
            }else if(quater == 2){
                quaterStr = "Q2(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-04-01");
                date_To = formatter.parse(year.toString() + "-06-30");
            }else if(quater == 3){
                quaterStr = "Q3(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-07-01");
                date_To = formatter.parse(year.toString() + "-09-30");
            }else{
                quaterStr = "Q4(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-10-01");
                date_To = formatter.parse(year.toString() + "-12-31");
            }

            List<Account> accountList = accountRepository.findAccountsByStatusOrderByAndDate(2, date_From, date_To);
            Long count = accountList.stream().count();
            countOrderResponse.setMonth(quaterStr);
            countOrderResponse.setCount(count);
            list.add(countOrderResponse);

            if(quater.equals(quater1) && year.equals(year1)){
                break;
            }

            if(quater == 4){
                quater = 1;
                year++;
            }else {
                quater++;
            }
        }
        return list;
    }

    @Override
    public List<CountOrderResponse> getCountAccountByYear(Integer year, Integer year1) throws ParseException {
        List<CountOrderResponse> list = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        while (true){
            CountOrderResponse countOrderResponse = new CountOrderResponse();
            Date date_From = new Date();
            Date date_To = new Date();
            String quaterStr = year.toString();
            date_From = formatter.parse(year.toString() + "-01-01");
            date_To = formatter.parse(year.toString() + "-12-31");
            List<Account> accountList = accountRepository.findAccountsByStatusOrderByAndDate(2, date_From, date_To);
            Long count = accountList.stream().count();
            countOrderResponse.setMonth(quaterStr);
            countOrderResponse.setCount(count);
            list.add(countOrderResponse);

            if(year.equals(year1)){
                break;
            }
            year++;
        }
        return list;
    }

    @Override
    public List<StatisticResponse> getStaticByMonth(String fromDate, String toDate) throws ParseException {
        String[] fromMonthStr = fromDate.split("-");
        String yearFrom = fromMonthStr[0].toString();
        String monthFrom = fromMonthStr[1].toString();
        //Integer yearFromInt= Integer.parseInt(yearFrom);
        Integer monthFromInt = Integer.parseInt(monthFrom);

        String[] toMonthStr = toDate.split("-");
        String yearTo = toMonthStr[0].toString();
        String monthTo = toMonthStr[1].toString();
        Integer yearToInt= Integer.parseInt(yearTo);
        Integer monthToInt = Integer.parseInt(monthTo);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<StatisticResponse> list = new ArrayList<>();

        while (true){
            StatisticResponse statisticResponse = new StatisticResponse();
            Date date_From = new Date();
            Date date_To = new Date();
            String month = "";

            if(monthFromInt == 1){
                month = "T1(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-01-01");
                date_To = formatter.parse(yearFrom + "-01-31");
            }
            if(monthFromInt == 2){
                month = "T2(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-02-01");
                date_To = formatter.parse(yearFrom + "-02-29");
            }
            if(monthFromInt == 3){
                month = "T3(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-03-01");
                date_To = formatter.parse(yearFrom + "-03-31");
            }
            if(monthFromInt == 4){
                month = "T4(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-04-01");
                date_To = formatter.parse(yearFrom + "-04-30");
            }
            if(monthFromInt == 5){
                month = "T5(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-05-01");
                date_To = formatter.parse(yearFrom + "-05-31");
            }
            if(monthFromInt == 6){
                month = "T6(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-06-01");
                date_To = formatter.parse(yearFrom + "-06-30");
            }
            if(monthFromInt == 7){
                month = "T7(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-07-01");
                date_To = formatter.parse(yearFrom + "-07-31");
            }
            if(monthFromInt == 8){
                month = "T8(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-08-01");
                date_To = formatter.parse(yearFrom + "-08-31");
            }
            if(monthFromInt == 9){
                month = "T9(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-09-01");
                date_To = formatter.parse(yearFrom + "-09-30");
            }
            if(monthFromInt == 10){
                month = "T10(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-10-01");
                date_To = formatter.parse(yearFrom + "-10-31");
            }
            if(monthFromInt == 11){
                month = "T11(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-11-01");
                date_To = formatter.parse(yearFrom + "-11-30");
            }
            if(monthFromInt == 12){
                month = "T12(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-12-01");
                date_To = formatter.parse(yearFrom + "-12-31");
            }

            Double value = invoiceRepository.getStaticByTime(date_From, date_To);
            statisticResponse.setName(month);

            statisticResponse.setValue(value == null ? 0L : value.longValue());
            statisticResponse.setText(String.valueOf(value == null ? 0L : value.longValue()));
            list.add(statisticResponse);

            if(monthFromInt == monthToInt && yearFrom.equals(yearTo)){
                break;
            }

            if(monthFromInt == 12){
                Integer yearFromInt= Integer.parseInt(yearFrom);
                yearFromInt++;
                yearFrom = yearFromInt.toString();
                monthFromInt = 1;
            }else {
                monthFromInt++;
            }
        }
        return list;
    }

    @Override
    public List<StatisticResponse> getStaticByQuater(Integer quater, Integer year, Integer quater1, Integer year1) throws ParseException {
        String dateArrFrom = null;
        String dateArrTo = null;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<StatisticResponse> list = new ArrayList<>();

        while(true){
            StatisticResponse statisticResponse = new StatisticResponse();
            Date date_From = new Date();
            Date date_To = new Date();
            String quaterStr = "";

            if(quater == 1){
                quaterStr = "Q1(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-01-01");
                date_To = formatter.parse(year.toString() + "-03-31");
            }else if(quater == 2){
                quaterStr = "Q2(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-04-01");
                date_To = formatter.parse(year.toString() + "-06-30");
            }else if(quater == 3){
                quaterStr = "Q3(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-07-01");
                date_To = formatter.parse(year.toString() + "-09-30");
            }else{
                quaterStr = "Q4(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-10-01");
                date_To = formatter.parse(year.toString() + "-12-31");
            }

            Double value = invoiceRepository.getStaticByTime(date_From, date_To);
            statisticResponse.setName(quaterStr);
            statisticResponse.setValue(value == null ? 0L : value.longValue());
            statisticResponse.setText(String.valueOf(value == null ? 0L : value.longValue()));
            list.add(statisticResponse);

            if(quater.equals(quater1) && year.equals(year1)){
                break;
            }

            if(quater == 4){
                quater = 1;
                year++;
            }else {
                quater++;
            }
        }
        return list;
    }

    @Override
    public List<StatisticResponse> getStaticByYear(Integer year, Integer year1) throws ParseException {
        List<StatisticResponse> list = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        while (true){
            StatisticResponse statisticResponse = new StatisticResponse();
            Date date_From = new Date();
            Date date_To = new Date();
            String quaterStr = year.toString();
            date_From = formatter.parse(year.toString() + "-01-01");
            date_To = formatter.parse(year.toString() + "-12-31");

            Double value = invoiceRepository.getStaticByTime(date_From, date_To);
            statisticResponse.setName(quaterStr);
            statisticResponse.setValue(value == null ? 0L : value.longValue());
            statisticResponse.setText(String.valueOf(value == null ? 0L : value.longValue()));
            list.add(statisticResponse);

            if(year.equals(year1)){
                break;
            }
            year++;
        }
        return list;
    }

    //Tính lợi nhuận
    @Override
    public List<CountOrderResponse> getProfitByMonth(String fromDate, String toDate) throws ParseException {
        String[] fromMonthStr = fromDate.split("-");
        String yearFrom = fromMonthStr[0].toString();
        String monthFrom = fromMonthStr[1].toString();
        //Integer yearFromInt= Integer.parseInt(yearFrom);
        Integer monthFromInt = Integer.parseInt(monthFrom);

        String[] toMonthStr = toDate.split("-");
        String yearTo = toMonthStr[0].toString();
        String monthTo = toMonthStr[1].toString();
        Integer yearToInt= Integer.parseInt(yearTo);
        Integer monthToInt = Integer.parseInt(monthTo);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<CountOrderResponse> list = new ArrayList<>();

        while (true){
            CountOrderResponse countOrderResponse = new CountOrderResponse();
            Date date_From = new Date();
            Date date_To = new Date();
            String month = "";

            if(monthFromInt == 1){
                month = "T1(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-01-01");
                date_To = formatter.parse(yearFrom + "-01-31");
            }
            if(monthFromInt == 2){
                month = "T2(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-02-01");
                date_To = formatter.parse(yearFrom + "-02-29");
            }
            if(monthFromInt == 3){
                month = "T3(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-03-01");
                date_To = formatter.parse(yearFrom + "-03-31");
            }
            if(monthFromInt == 4){
                month = "T4(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-04-01");
                date_To = formatter.parse(yearFrom + "-04-30");
            }
            if(monthFromInt == 5){
                month = "T5(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-05-01");
                date_To = formatter.parse(yearFrom + "-05-31");
            }
            if(monthFromInt == 6){
                month = "T6(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-06-01");
                date_To = formatter.parse(yearFrom + "-06-30");
            }
            if(monthFromInt == 7){
                month = "T7(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-07-01");
                date_To = formatter.parse(yearFrom + "-07-31");
            }
            if(monthFromInt == 8){
                month = "T8(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-08-01");
                date_To = formatter.parse(yearFrom + "-08-31");
            }
            if(monthFromInt == 9){
                month = "T9(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-09-01");
                date_To = formatter.parse(yearFrom + "-09-30");
            }
            if(monthFromInt == 10){
                month = "T10(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-10-01");
                date_To = formatter.parse(yearFrom + "-10-31");
            }
            if(monthFromInt == 11){
                month = "T11(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-11-01");
                date_To = formatter.parse(yearFrom + "-11-30");
            }
            if(monthFromInt == 12){
                month = "T12(" + yearFrom + ")";
                date_From = formatter.parse(yearFrom + "-12-01");
                date_To = formatter.parse(yearFrom + "-12-31");
            }

            Double value = invoiceRepository.getStaticByTime(date_From, date_To);
            if(Objects.isNull(value)){
                value = 0.0;
            }
            Double value1 = warehouseReceiptRepository.getProfitByTime(date_From, date_To);
            if(Objects.isNull(value1)){
                value1 = 0.0;
            }
            Double profit = value - value1;

            countOrderResponse.setMonth(month);
            countOrderResponse.setCount(profit.longValue());
            list.add(countOrderResponse);

            if(monthFromInt == monthToInt && yearFrom.equals(yearTo)){
                break;
            }

            if(monthFromInt == 12){
                Integer yearFromInt= Integer.parseInt(yearFrom);
                yearFromInt++;
                yearFrom = yearFromInt.toString();
                monthFromInt = 1;
            }else {
                monthFromInt++;
            }
        }
        return list;
    }

    @Override
    public List<CountOrderResponse> getProfitByQuater(Integer quater, Integer year, Integer quater1, Integer year1) throws ParseException {
        String dateArrFrom = null;
        String dateArrTo = null;
        List<CountOrderResponse> list = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        while (true){
            CountOrderResponse countOrderResponse = new CountOrderResponse();
            Date date_From = new Date();
            Date date_To = new Date();
            String quaterStr = "";

            if(quater == 1){
                quaterStr = "Q1(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-01-01");
                date_To = formatter.parse(year.toString() + "-03-31");
            }else if(quater == 2){
                quaterStr = "Q2(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-04-01");
                date_To = formatter.parse(year.toString() + "-06-30");
            }else if(quater == 3){
                quaterStr = "Q3(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-07-01");
                date_To = formatter.parse(year.toString() + "-09-30");
            }else{
                quaterStr = "Q4(" + year.toString() + ")";
                date_From = formatter.parse(year.toString() + "-10-01");
                date_To = formatter.parse(year.toString() + "-12-31");
            }

            Double value = invoiceRepository.getStaticByTime(date_From, date_To);
            if(Objects.isNull(value)){
                value = 0.0;
            }
            Double value1 = warehouseReceiptRepository.getProfitByTime(date_From, date_To);
            if(Objects.isNull(value1)){
                value1 = 0.0;
            }
            Double profit = value - value1;

            countOrderResponse.setMonth(quaterStr);
            countOrderResponse.setCount(profit.longValue());
            list.add(countOrderResponse);

            if(quater.equals(quater1) && year.equals(year1)){
                break;
            }

            if(quater == 4){
                quater = 1;
                year++;
            }else {
                quater++;
            }
        }
        return list;
    }

    @Override
    public List<CountOrderResponse> getProfitByYear(Integer year, Integer year1) throws ParseException {
        List<CountOrderResponse> list = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        while (true){
            CountOrderResponse countOrderResponse = new CountOrderResponse();
            Date date_From = new Date();
            Date date_To = new Date();
            String quaterStr = year.toString();
            date_From = formatter.parse(year.toString() + "-01-01");
            date_To = formatter.parse(year.toString() + "-12-31");

            Double value = invoiceRepository.getStaticByTime(date_From, date_To);
            if(Objects.isNull(value)){
                value = 0.0;
            }
            Double value1 = warehouseReceiptRepository.getProfitByTime(date_From, date_To);
            if(Objects.isNull(value1)){
                value1 = 0.0;
            }
            Double profit = value - value1;

            countOrderResponse.setMonth(quaterStr);
            countOrderResponse.setCount(profit.longValue());
            list.add(countOrderResponse);

            if(year.equals(year1)){
                break;
            }
            year++;
        }
        return list;
    }
}
