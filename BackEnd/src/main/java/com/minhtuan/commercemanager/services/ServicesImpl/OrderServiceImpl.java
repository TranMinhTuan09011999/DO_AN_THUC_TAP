package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ColorDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.maper.OrderMapper;
import com.minhtuan.commercemanager.message.request.OrderRequest;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.repository.CategoryRepository;
import com.minhtuan.commercemanager.repository.CustomerRepository;
import com.minhtuan.commercemanager.repository.OrderRepository;
import com.minhtuan.commercemanager.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

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
        Customer customer = customerRepository.findCustomerById(orderRequest.getCustomerId());
        order.setCustomer(customer);
        orderRepository.save(order);
        return order.getOrderId();
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderResponse> orderResponseList = orderList.stream().map(order -> orderMapper.toOrderResponse(order)).collect(Collectors.toList());
        return orderResponseList;
    }
}
