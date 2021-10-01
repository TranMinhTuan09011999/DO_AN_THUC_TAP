package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findAllByOrderOrderByOrderDetailIdDesc(Order order);
}
