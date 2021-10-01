package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.OrderDetailDTO;
import com.minhtuan.commercemanager.maper.OrderDetailMapper;
import com.minhtuan.commercemanager.message.request.OrderDetailRequest;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.model.OrderDetail;
import com.minhtuan.commercemanager.model.ProductDetail;
import com.minhtuan.commercemanager.repository.CategoryRepository;
import com.minhtuan.commercemanager.repository.OrderDetailRepository;
import com.minhtuan.commercemanager.repository.OrderRepository;
import com.minhtuan.commercemanager.repository.ProductDetailRepository;
import com.minhtuan.commercemanager.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public void save(OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = new OrderDetail();
        Order order = orderRepository.findOrderByOrderId(orderDetailRequest.getOrderId());
        orderDetail.setOrder(order);
        ProductDetail productDetail = productDetailRepository.findByProductDetailId(orderDetailRequest.getProductDetailId()).get();
        orderDetail.setProductDetail(productDetail);
        orderDetail.setPrice(orderDetailRequest.getPrice());
        orderDetail.setQuantity(orderDetailRequest.getQuantity());
        orderDetail.setDiscount(orderDetailRequest.getDiscount());
        System.out.println(orderDetail.getDiscount());
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetailDTO> getOrderDetailsByOrderId(String orderId) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderOrderByOrderDetailIdDesc(order);
        List<OrderDetailDTO> orderDetailDTOList = orderDetailList.stream().map(orderDetail -> orderDetailMapper.toDTO(orderDetail)).collect(Collectors.toList());
        return orderDetailDTOList;
    }
}
