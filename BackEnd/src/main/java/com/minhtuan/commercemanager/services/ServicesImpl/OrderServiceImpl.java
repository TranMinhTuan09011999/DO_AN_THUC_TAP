package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ColorDTO;
import com.minhtuan.commercemanager.dto.OrderDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.maper.OrderMapper;
import com.minhtuan.commercemanager.message.request.OrderRequest;
import com.minhtuan.commercemanager.message.request.ReceiptRequest;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.model.Invoice;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.repository.*;
import com.minhtuan.commercemanager.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public String save(OrderRequest orderRequest) {
        Order order = new Order();
        List<Order> orderList = orderRepository.findAll();
        String idNew = "";
        if(orderList.size() > 0){
            Integer end = orderList.size() - 1;
            String id = orderList.get(end).getOrderId();
            String IdInt = id.substring(2);
            String IdBegin = id.substring(0,2);
            Integer newIdInt = Integer.parseInt(IdInt);
            newIdInt += 1;
            String newIdString = newIdInt.toString();
            if(newIdString.length() == 1)
            {
                newIdString = "0000" + newIdString;
            }else if(newIdString.length() == 2)
            {
                newIdString = "000" + newIdString;
            }else if(newIdString.length() == 3)
            {
                newIdString = "00" + newIdString;
            }
            else if(newIdString.length() == 4)
            {
                newIdString = "0" + newIdString;
            }
            idNew = IdBegin + newIdString;
        }else {
            idNew = "PD00001";
        }
        order.setOrderId(idNew);
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        java.time.LocalDateTime.now();
        order.setDateOfOrder(date);
        order.setFirstnameOfReveiver(orderRequest.getFirstnameOfReveiver());
        order.setLastnameOfReveiver(orderRequest.getLastnameOfReveiver());
        order.setAddressOfReceiver(orderRequest.getAddressOfReceiver());
        order.setPhoneOfReceiver(orderRequest.getPhoneOfReceiver());
        order.setStatus(1);
        order.setPayment(0);
        Customer customer = customerRepository.findCustomerById(orderRequest.getCustomerId());
        order.setCustomer(customer);
        orderRepository.save(order);
        return order.getOrderId();
    }

    @Override
    public List<OrderResponse> getAllOrder(Integer status) {
        List<Order> orderList = orderRepository.findOrdersByStatusOrderByOrderIdDesc(status);
        List<OrderResponse> orderResponseList = orderList.stream().map(order -> orderMapper.toOrderResponse(order)).collect(Collectors.toList());
        return orderResponseList;
    }

    @Override
    public List<OrderDTO> getAllDeliveryOrder(Integer status) {
        List<Order> orderList = orderRepository.findOrdersByStatusOrderByOrderIdDesc(status);
        List<OrderDTO> orderDTOList = orderList.stream().map(order -> orderMapper.toDTO(order)).collect(Collectors.toList());
        return orderDTOList;
    }

    @Override
    public OrderResponse updateStatusOrder(String orderId, Integer status) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        order.setStatus(status);
        Order updateOrder = orderRepository.save(order);
        OrderResponse orderResponse = orderMapper.toOrderResponse(updateOrder);
        return orderResponse;
    }

    @Override
    public OrderResponse updatePaymentOrder(String orderId, Integer payment) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        order.setPayment(payment);
        Order updateOrder = orderRepository.save(order);
        OrderResponse orderResponse = orderMapper.toOrderResponse(updateOrder);
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrderByCustomerId(String customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        List<Order> orderList = orderRepository.findAllByCustomerOrderByOrderIdDesc(customer);
        List<OrderResponse> orderResponseList = orderList.stream().map(order -> orderMapper.toOrderResponse(order)).collect(Collectors.toList());
        return orderResponseList;
    }

    @Override
    public OrderDTO updateOrder(String orderId, String employeeId, String deliveryEmployeeId) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        java.time.LocalDateTime.now();
        order.setDeliveryDate(date);
        Employee employee = employeeRepository.getById(employeeId);
        order.setEmployee(employee);
        System.out.println(deliveryEmployeeId);
        Employee employee1 = employeeRepository.getById(deliveryEmployeeId);
        order.setDeliveryEmployee(employee1);
        order.setStatus(3);
        Order updateOrder = orderRepository.save(order);
        OrderDTO orderDTO = orderMapper.toDTO(updateOrder);
        return orderDTO;
    }

    @Override
    public String saveReceipt(ReceiptRequest receiptRequest) {
        Invoice invoice = new Invoice();
        List<Invoice> invoiceList = (List<Invoice>) invoiceRepository.findAll();
        String idNew = "";
        if(invoiceList.size() > 0){
            Integer end = invoiceList.size() - 1;
            String id = invoiceList.get(end).getInvoiceId();
            String IdInt = id.substring(2);
            String IdBegin = id.substring(0,2);
            Integer newIdInt = Integer.parseInt(IdInt);
            newIdInt += 1;
            String newIdString = newIdInt.toString();
            if(newIdString.length() == 1)
            {
                newIdString = "0000" + newIdString;
            }else if(newIdString.length() == 2)
            {
                newIdString = "000" + newIdString;
            }else if(newIdString.length() == 3)
            {
                newIdString = "00" + newIdString;
            }
            else if(newIdString.length() == 4)
            {
                newIdString = "0" + newIdString;
            }
            idNew = IdBegin + newIdString;
        }else {
            idNew = "HD00001";
        }
        invoice.setInvoiceId(idNew);
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        java.time.LocalDateTime.now();
        invoice.setDate(date);
        invoice.setAmount(receiptRequest.getAmount());
        invoice.setTaxId(receiptRequest.getTaxId());
        Order order = orderRepository.findOrderByOrderId(receiptRequest.getOrderId());
        invoice.setOrderInvoice(order);
        invoiceRepository.save(invoice);
        return invoice.getInvoiceId();
    }

    @Override
    public List<OrderResponse> getAllOrderByStatusAndDate(Integer status, String fromDate, String toDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = sdf.parse(fromDate);
        Date dateTo = sdf.parse(toDate);
        List<Order> orderList = orderRepository.findOrdersByStatusOrderByOrderIdDescAndDate(status, dateFrom, dateTo);
        List<OrderResponse> orderResponseList = orderList.stream().map(order -> orderMapper.toOrderResponse(order)).collect(Collectors.toList());
        return orderResponseList;
    }

    @Override
    public List<OrderDTO> getAllDeliveryOrderByDate(Integer status, String fromDate, String toDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = sdf.parse(fromDate);
        Date dateTo = sdf.parse(toDate);
        List<Order> orderList = orderRepository.findOrdersByStatusOrderByOrderIdDescAndDate(status, dateFrom, dateTo);
        List<OrderDTO> orderDTOList = orderList.stream().map(order -> orderMapper.toDTO(order)).collect(Collectors.toList());
        return orderDTOList;
    }

    @Override
    public List<OrderResponse> getAllOrderByCustomerId(String customerId, Integer status) {
        Customer customer = customerRepository.getById(customerId);
        List<Order> orderList = orderRepository.findOrdersByCustomerAndStatusOrderByOrderIdDesc(customer, status);
        List<OrderResponse> orderResponseList = orderList.stream().map(order -> orderMapper.toOrderResponse(order)).collect(Collectors.toList());
        return orderResponseList;
    }

    @Override
    public List<OrderDTO> getAllDeliveryOrderByCustomer(String customerId, Integer status) {
        Customer customer = customerRepository.getById(customerId);
        List<Order> orderList = orderRepository.findOrdersByCustomerAndStatusOrderByOrderIdDesc(customer, status);
        List<OrderDTO> orderDTOList = orderList.stream().map(order -> orderMapper.toDTO(order)).collect(Collectors.toList());
        return orderDTOList;
    }

    @Override
    public Long newOrderCount() {
        Long count = orderRepository.countAllByStatus(2);
        return count;
    }
}
