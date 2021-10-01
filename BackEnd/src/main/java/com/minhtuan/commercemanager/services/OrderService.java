package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.OrderDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.message.request.OrderRequest;
import com.minhtuan.commercemanager.message.request.ProductRequest;
import com.minhtuan.commercemanager.message.request.ReceiptRequest;
import com.minhtuan.commercemanager.message.response.OrderResponse;

import java.text.ParseException;
import java.util.List;

public interface OrderService {
    public String save(OrderRequest orderRequest);
    public List<OrderResponse> getAllOrder(Integer status);
    public List<OrderDTO> getAllDeliveryOrder(Integer status);
    OrderResponse updateStatusOrder(String orderId, Integer status);
    public List<OrderResponse> getAllOrderByCustomerId(String customerId);
    OrderDTO updateOrder(String orderId, String employeeId, String deliveryEmployeeId);
    public String saveReceipt(ReceiptRequest receiptRequest);
    public List<OrderResponse> getAllOrderByStatusAndDate(Integer status, String fromDate, String toDate) throws ParseException;
    public List<OrderDTO> getAllDeliveryOrderByDate(Integer status, String fromDate, String toDate) throws ParseException;
    public List<OrderResponse> getAllOrderByCustomerId(String customerId, Integer status);
    public List<OrderDTO> getAllDeliveryOrderByCustomer(String customerId, Integer status);
    public Long newOrderCount();
    public OrderResponse updatePaymentOrder(String orderId, Integer payment);
}
