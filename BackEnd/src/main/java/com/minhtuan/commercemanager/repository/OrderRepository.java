package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    Order findOrderByOrderId(String orderId);
}
