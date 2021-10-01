package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ColorDTO;
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
import com.minhtuan.commercemanager.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Override
    public List<StatisticResponse> getSaleStatistic(String fromDate, String toDate) throws ParseException {
        String[] fromDateArr = fromDate.split("-");
        String[] toDateArr = toDate.split("-");
        Integer fromMonth = Integer.parseInt(fromDateArr[1]);
        Integer fromYear = Integer.parseInt(fromDateArr[0]);
        Integer toMonth = Integer.parseInt(toDateArr[1]);
        Integer toYear = Integer.parseInt(toDateArr[0]);
        List<StatisticResponse> list = new ArrayList<>();
        while(true){
            StatisticResponse statisticResponse = new StatisticResponse();
            statisticResponse.setName(fromMonth + "(" + fromYear + ")");
            Double total = invoiceRepository.getAmountByMonth(fromMonth, fromYear);
            statisticResponse.setValue(total);
            statisticResponse.setText(String.valueOf(total));
            list.add(statisticResponse);
            String fromYearStr = String.valueOf(fromYear);
            String toYearStr = String.valueOf(toYear);
            if(fromMonth == toMonth && fromYearStr.equals(toYearStr) == true){
                break;
            }
            if(fromMonth == 12){
                fromMonth = 1;
                fromYear = fromYear+1;
            }
            else{
                fromMonth++;
            }
        }
        return list;
//        List<StatisticResponse> list = new ArrayList<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String[] str = fromDate.split("-");
//        String yearFromDate = str[0].toString();
//        String monthFromDate = str[1].toString();
//        Integer yearFromDate1 = Integer.parseInt(yearFromDate);
//        Integer monthFromDate1 = Integer.parseInt(monthFromDate);
//
//        String[] str1 = toDate.split("-");
//        String yearToDate = str1[0].toString();
//        String monthToDate = str1[1].toString();
//        Integer yearToDate1 = Integer.parseInt(yearToDate);
//        Integer monthToDate1 = Integer.parseInt(monthToDate);
//
//        Date dateFrom = sdf.parse(fromDate);
//        Date dateTo = sdf.parse(toDate);
//
//        List<Invoice> invoiceList = invoiceRepository.findAll();
//        List<InvoiceDTO> invoiceDTOList = invoiceList.stream().map(invoice -> invoiceMapper.toDTO(invoice)).collect(Collectors.toList());
//        if(monthFromDate.equals(monthToDate) && yearFromDate.equals(yearToDate))
//        {
//            System.out.println(yearToDate1);
//            Double total = 0.0;
//            for(int i=0; i<invoiceDTOList.size() -1; i++){
//                System.out.println(invoiceDTOList.get(i).getAmount());
//                Date date = invoiceDTOList.get(i).getDate();
//                if(dateFrom.compareTo(date) <= 0 && dateTo.compareTo(date) >= 0){
//                    total += invoiceDTOList.get(i).getAmount();
//                }
//            }
//            StatisticResponse statisticResponse = new StatisticResponse();
//            statisticResponse.setName(monthToDate + "("+ yearToDate +")");
//            statisticResponse.setValue(total);
//            String text = String.valueOf(total);
//            statisticResponse.setText(text);
//            list.add(statisticResponse);
//            return list;
//        }
//
//        Date date_From = new Date();
//        Date date_To = new Date();
//        if(monthFromDate1 == 1){
//            String month1 = yearFromDate + "-01-31";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month1);
//        }else if(monthFromDate1 == 2){
//            String month2 = yearFromDate + "-02-29";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month2);
//        }else if(monthFromDate1 == 3){
//            String month3 = yearFromDate + "-03-31";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month3);
//        }else if(monthFromDate1 == 4){
//            String month4 = yearFromDate + "-04-30";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month4);
//        }else if(monthFromDate1 == 5){
//            String month5 = yearFromDate + "-05-31";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month5);
//        }else if(monthFromDate1 == 6){
//            String month6 = yearFromDate + "-06-30";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month6);
//        }else if(monthFromDate1 == 7){
//            String month7 = yearFromDate + "-07-31";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month7);
//        }else if(monthFromDate1 == 8){
//            String month8 = yearFromDate + "-08-31";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month8);
//        }else if(monthFromDate1 == 9){
//            String month9 = yearFromDate + "-09-30";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month9);
//        }else if(monthFromDate1 == 10){
//            String month10 = yearFromDate + "-10-31";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month10);
//        }else if(monthFromDate1 == 11){
//            String month11 = yearFromDate + "-11-30";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month11);
//        }else{
//            System.out.println("Th12");
//            String month12 = yearFromDate + "-12-31";
//            date_From = sdf.parse(fromDate);
//            date_To = sdf.parse(month12);
//        }
//        Double total = 0.0;
//        for(int i=0; i<invoiceDTOList.size(); i++){
//            Date date = invoiceDTOList.get(i).getDate();
//            if(date_From.compareTo(date) <= 0 && date_To.compareTo(date) >= 0){
//                total += invoiceDTOList.get(i).getAmount();
//            }
//        }
//        StatisticResponse statisticResponse = new StatisticResponse();
//        String mothFromStr = String.valueOf(monthFromDate1);
//        String yearFromStr = String.valueOf(yearFromDate1);
//        statisticResponse.setName(mothFromStr + "("+ yearFromStr +")");
//        statisticResponse.setValue(total);
//        String text = String.valueOf(total);
//        statisticResponse.setText(text);
//        list.add(statisticResponse);
//
//        if(monthFromDate1 == 12){
//            yearFromDate1++;
//            monthFromDate1 = 1;
//        }else {
//            monthFromDate1++;
//        }
//
//        while(true){
//            Date date_From1 = new Date();
//            Date date_To1 = new Date();
//            if(monthFromDate1 == 1){
//                date_From1 = sdf.parse(yearToDate + "-01-01");
//                date_To1 = sdf.parse(yearToDate + "-01-31");
//            }else if(monthFromDate1 == 2){
//                date_From1 = sdf.parse(yearToDate + "-02-01");
//                date_To1 = sdf.parse(yearToDate + "-02-29");
//            }else if(monthFromDate1 == 3){
//                date_From1 = sdf.parse(yearToDate + "-03-01");
//                date_To1 = sdf.parse(yearToDate + "-03-31");
//            }else if(monthFromDate1 == 4){
//                date_From1 = sdf.parse(yearToDate + "-04-01");
//                date_To1 = sdf.parse(yearToDate + "-04-30");
//            }else if(monthFromDate1 == 5){
//                date_From1 = sdf.parse(yearToDate + "-05-01");
//                date_To1 = sdf.parse(yearToDate + "-05-30");
//            }else if(monthFromDate1 == 6){
//                date_From1 = sdf.parse(yearToDate + "-06-01");
//                date_To1 = sdf.parse(yearToDate + "-06-31");
//            }else if(monthFromDate1 == 7){
//                date_From1 = sdf.parse(yearToDate + "-07-01");
//                date_To1 = sdf.parse(yearToDate + "-07-31");
//            }else if(monthFromDate1 == 8){
//                date_From1 = sdf.parse(yearToDate + "-08-01");
//                date_To1 = sdf.parse(yearToDate + "-08-30");
//            }else if(monthFromDate1 == 9){
//                date_From1 = sdf.parse(yearToDate + "-09-01");
//                date_To1 = sdf.parse(yearToDate + "-09-30");
//            }else if(monthFromDate1 == 10){
//                date_From1 = sdf.parse(yearToDate + "-10-01");
//                date_To1 = sdf.parse(yearToDate + "-10-31");
//            }else if(monthFromDate1 == 11){
//                date_From1 = sdf.parse(yearToDate + "-11-01");
//                date_To1 = sdf.parse(yearToDate + "-11-30");
//            }else{
//                date_From1 = sdf.parse(yearToDate + "-12-01");
//                date_To1 = sdf.parse(yearToDate + "-12-31");
//            }
//            Double total1 = 0.0;
//            for(int i=0; i<invoiceDTOList.size(); i++){
//                Date date = invoiceDTOList.get(i).getDate();
//                if(date_From1.compareTo(date) <= 0 && date_To1.compareTo(date) >= 0){
//                    total1 += invoiceDTOList.get(i).getAmount();
//                }
//            }
//            StatisticResponse statisticResponse1 = new StatisticResponse();
//            String mothFromStr1 = String.valueOf(monthFromDate1);
//            String yearFromStr1 = String.valueOf(yearFromDate1);
//            statisticResponse1.setName(mothFromStr1 + "("+ yearFromStr1 +")");
//            statisticResponse1.setValue(total1);
//            String text1 = String.valueOf(total1);
//            statisticResponse1.setText(text1);
//            list.add(statisticResponse1);
//
//            if(monthFromDate1 == 12){
//                yearFromDate1++;
//                monthFromDate1 = 1;
//            }else {
//                monthFromDate1++;
//            }
//
//            String monthFromDate1Str = String.valueOf(monthFromDate1);
//            String yearFromDate1Str = String.valueOf(yearFromDate1);
//
//            String monthToDateStr = String.valueOf(monthToDate1);
//            String yearToDateStr = String.valueOf(yearToDate1);
//
//            if(monthFromDate1Str.equals(monthToDateStr) == true && yearFromDate1Str.equals(yearToDateStr) == true){
//                String dateFinish = String.valueOf(yearFromDate1) + "-" + String.valueOf(monthFromDate1) + "-01";
//                date_From1 = sdf.parse(dateFinish);
//                date_To1 = sdf.parse(toDate);
//                total1 = 0.0;
//                for(int i=0; i<invoiceDTOList.size(); i++){
//                    Date date = invoiceDTOList.get(i).getDate();
//                    String date1 = sdf.format(date);
//                    Date date2 = sdf.parse(date1);
//                    if(date_From1.compareTo(date2) < 0 && date_To1.compareTo(date2) > 0){
//                        total1 += invoiceDTOList.get(i).getAmount();
//                    }
//                }
//                statisticResponse1 = new StatisticResponse();
//                mothFromStr1 = String.valueOf(monthFromDate1);
//                yearFromStr1 = String.valueOf(yearFromDate1);
//                statisticResponse1.setName(mothFromStr1 + "("+ yearFromStr1 +")");
//                statisticResponse1.setValue(total1);
//                text = String.valueOf(total1);
//                statisticResponse1.setText(text);
//                list.add(statisticResponse1);
//                break;
//            }
//        }
//        return list;
    }

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
}
