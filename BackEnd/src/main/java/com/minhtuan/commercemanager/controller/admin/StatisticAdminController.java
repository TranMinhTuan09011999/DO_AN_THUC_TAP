package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.HotSellingProductDTO;
import com.minhtuan.commercemanager.dto.SizeDTO;
import com.minhtuan.commercemanager.message.response.CountOrderResponse;
import com.minhtuan.commercemanager.message.response.StatisticResponse;
import com.minhtuan.commercemanager.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class StatisticAdminController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/statistic/{fromDate}/{toDate}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getStatistic(@PathVariable(value = "fromDate") String fromDate, @PathVariable(value = "toDate") String toDate) throws ParseException {
        List<StatisticResponse> statisticResponseList = statisticService.getStaticByMonth(fromDate, toDate);
        return ResponseEntity.ok().body(statisticResponseList);
    }

    @GetMapping("/statistic/quater/{quater}/{year}/{quater1}/{year1}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getStatisticbyQuater(@PathVariable(value = "quater") Integer quater, @PathVariable(value = "year") Integer year, @PathVariable(value = "quater1") Integer quater1, @PathVariable(value = "year1") Integer year1) throws ParseException {
        List<StatisticResponse> statisticResponseList = statisticService.getStaticByQuater(quater, year, quater1, year1);
        return ResponseEntity.ok().body(statisticResponseList);
    }

    @GetMapping("/statistic/year/{year}/{year1}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getStatisticbyYear(@PathVariable(value = "year") Integer year, @PathVariable(value = "year1") Integer year1) throws ParseException {
        List<StatisticResponse> statisticResponseList = statisticService.getStaticByYear(year, year1);
        return ResponseEntity.ok().body(statisticResponseList);
    }

    @GetMapping("/statistic/orderCountMonthly")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getOrderCountMonthly() throws ParseException {
        List<CountOrderResponse> countOrderResponseList = statisticService.getCountOrderMonthly();
        return ResponseEntity.ok().body(countOrderResponseList);
    }

    @GetMapping("/statistic/accountCountMonthly")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getAccountCountMonthly() throws ParseException {
        List<CountOrderResponse> countOrderResponseList = statisticService.getCountAccountMonthly();
        return ResponseEntity.ok().body(countOrderResponseList);
    }

    @GetMapping("/statistic/hotSellingProduct/{month}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getHotSellingProduct(@PathVariable(value = "month") String month) throws ParseException {
        List<HotSellingProductDTO> hotSellingProductDTOList = statisticService.getHotSellingProduct(month);
        return ResponseEntity.ok().body(hotSellingProductDTOList);
    }

    @GetMapping("/statistic/hotSellingProduct/{fromDate}/{toDate}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getHotSellingProduct(@PathVariable(value = "fromDate") String fromDate, @PathVariable(value = "toDate") String toDate) throws ParseException {
        List<HotSellingProductDTO> hotSellingProductDTOList = statisticService.getHotSellingProductByMonthFromTo(fromDate, toDate);
        return ResponseEntity.ok().body(hotSellingProductDTOList);
    }

    @GetMapping("/statistic/hotSellingProduct/quater/{quater}/{year}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getHotSellingProductbyQuater(@PathVariable(value = "quater") Integer quater, @PathVariable(value = "year") Integer year) throws ParseException {
        List<HotSellingProductDTO> hotSellingProductDTOList = statisticService.getHotSellingProductByQuater(quater, year, null, null);
        return ResponseEntity.ok().body(hotSellingProductDTOList);
    }

    @GetMapping("/statistic/hotSellingProduct/quater/{quater}/{year}/{quater1}/{year1}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getHotSellingProductbyQuater(@PathVariable(value = "quater") Integer quater, @PathVariable(value = "year") Integer year, @PathVariable(value = "quater1") Integer quater1, @PathVariable(value = "year1") Integer year1) throws ParseException {
        List<HotSellingProductDTO> hotSellingProductDTOList = statisticService.getHotSellingProductByQuater(quater, year, quater1, year1);
        return ResponseEntity.ok().body(hotSellingProductDTOList);
    }

    @GetMapping("/statistic/hotSellingProduct/year/{year}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getHotSellingProductbyYear(@PathVariable(value = "year") Integer year) throws ParseException {
        List<HotSellingProductDTO> hotSellingProductDTOList = statisticService.getHotSellingProductByYear(year, null);
        return ResponseEntity.ok().body(hotSellingProductDTOList);
    }

    @GetMapping("/statistic/hotSellingProduct/year/{year}/{year1}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getHotSellingProductbyYearFromTo(@PathVariable(value = "year") Integer year, @PathVariable(value = "year1") Integer year1) throws ParseException {
        List<HotSellingProductDTO> hotSellingProductDTOList = statisticService.getHotSellingProductByYear(year, year1);
        return ResponseEntity.ok().body(hotSellingProductDTOList);
    }

    @GetMapping("/statistic/countCustomer/month/{monthFrom}/{monthTo}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getCountCustomerYearFromTo(@PathVariable(value = "monthFrom") String monthFrom, @PathVariable(value = "monthTo") String monthTo) throws ParseException {
        List<CountOrderResponse> countOrderResponseList = statisticService.getCountAccountByMonth(monthFrom, monthTo);
        return ResponseEntity.ok().body(countOrderResponseList);
    }

    @GetMapping("/statistic/countCustomer/quater/{quater}/{year}/{quater1}/{year1}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getCountCustomerQuaterFromTo(@PathVariable(value = "quater") Integer quater, @PathVariable(value = "year") Integer year, @PathVariable(value = "quater1") Integer quater1, @PathVariable(value = "year1") Integer year1) throws ParseException {
        List<CountOrderResponse> countOrderResponseList = statisticService.getCountAccountByQuater(quater, year, quater1, year1);
        return ResponseEntity.ok().body(countOrderResponseList);
    }

    @GetMapping("/statistic/countCustomer/year/{year}/{year1}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getCountCustomerYearFromTo(@PathVariable(value = "year") Integer year, @PathVariable(value = "year1") Integer year1) throws ParseException {
        List<CountOrderResponse> countOrderResponseList = statisticService.getCountAccountByYear(year, year1);
        return ResponseEntity.ok().body(countOrderResponseList);
    }

    // Tính lợi nhuận
    // Theo tháng
    @GetMapping("/statistic/profit/month/{monthFrom}/{monthTo}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProfixByMonth(@PathVariable(value = "monthFrom") String monthFrom, @PathVariable(value = "monthTo") String monthTo) throws ParseException {
        List<CountOrderResponse> countOrderResponseList = statisticService.getProfitByMonth(monthFrom, monthTo);
        return ResponseEntity.ok().body(countOrderResponseList);
    }
    // Theo quý
    @GetMapping("/statistic/profit/quater/{quater}/{year}/{quater1}/{year1}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProfixByQuater(@PathVariable(value = "quater") Integer quater, @PathVariable(value = "year") Integer year, @PathVariable(value = "quater1") Integer quater1, @PathVariable(value = "year1") Integer year1) throws ParseException {
        List<CountOrderResponse> countOrderResponseList = statisticService.getProfitByQuater(quater, year, quater1, year1);
        return ResponseEntity.ok().body(countOrderResponseList);
    }
    // Theo năm
    @GetMapping("/statistic/profit/year/{year}/{year1}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProfixByYear(@PathVariable(value = "year") Integer year, @PathVariable(value = "year1") Integer year1) throws ParseException {
        List<CountOrderResponse> countOrderResponseList = statisticService.getProfitByYear(year, year1);
        return ResponseEntity.ok().body(countOrderResponseList);
    }
}
