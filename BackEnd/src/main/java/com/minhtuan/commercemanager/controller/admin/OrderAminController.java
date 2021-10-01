package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.OrderDTO;
import com.minhtuan.commercemanager.message.request.ReceiptRequest;
import com.minhtuan.commercemanager.message.response.ApiResponse;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class OrderAminController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order/all/{status}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getOrderList(@PathVariable(value = "status") Integer status){
        List<OrderResponse> orderResponseList = orderService.getAllOrder(status);
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseList);
    }

    @GetMapping("/order/delivery/{status}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getDeliveryOrderList(@PathVariable(value = "status") Integer status){
        List<OrderDTO> orderDTOList = orderService.getAllDeliveryOrder(status);
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(orderDTOList);
    }

    @PutMapping("/order/update/{orderId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateOrder(@PathVariable(value = "orderId") String orderId, @RequestBody HashMap<String, String> update){
        try {
            String employeeId = update.get("employeeId");
            String employeeIdOfDelivery = update.get("employeeIdDelivery");
            OrderDTO orderDTO = orderService.updateOrder(orderId, employeeId, employeeIdOfDelivery);
            return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @PostMapping("/invoice")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> saveReciept(@RequestBody ReceiptRequest receiptRequest){
        orderService.saveReceipt(receiptRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/order/all/{status}/{fromDate}/{toDate}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getOrderListByDate(@PathVariable(value = "status") Integer status, @PathVariable(value = "fromDate") String fromDate, @PathVariable(value = "toDate") String toDate) throws ParseException {
        System.out.println(fromDate);
        System.out.println(toDate);
        List<OrderResponse> orderResponseList = orderService.getAllOrderByStatusAndDate(status, fromDate, toDate);
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseList);
    }

    @GetMapping("/order/delivery/{status}/{fromDate}/{toDate}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getDeliveryOrderListByDate(@PathVariable(value = "status") Integer status, @PathVariable(value = "fromDate") String fromDate, @PathVariable(value = "toDate") String toDate) throws ParseException {
        List<OrderDTO> orderDTOList = orderService.getAllDeliveryOrderByDate(status, fromDate, toDate);
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(orderDTOList);
    }

    @GetMapping("/order/count")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getEmployeeCount(){
        Long count = orderService.newOrderCount();
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }
}
