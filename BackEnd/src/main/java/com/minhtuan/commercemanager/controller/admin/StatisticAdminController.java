package com.minhtuan.commercemanager.controller.admin;

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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getStatistic(@PathVariable(value = "fromDate") String fromDate, @PathVariable(value = "toDate") String toDate) throws ParseException {
        List<StatisticResponse> statisticResponseList = statisticService.getSaleStatistic(fromDate, toDate);
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

}
