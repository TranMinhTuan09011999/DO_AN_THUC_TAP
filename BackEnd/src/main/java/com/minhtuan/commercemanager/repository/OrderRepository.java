package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    Order findOrderByOrderId(String orderId);
    List<Order> findOrdersByStatusOrderByOrderIdDesc(Integer status);
    List<Order> findOrdersByCustomerAndStatusOrderByOrderIdDesc(Customer customer, Integer status);
    List<Order> findAllByCustomerOrderByOrderIdDesc(Customer customer);

    @Query(
            value = "SELECT o FROM Order o " +
                    "WHERE o.dateOfOrder >= :fromDate AND o.dateOfOrder <= :toDate AND o.status = :status " +
                    "ORDER BY o.orderId DESC"
    )
    List<Order> findOrdersByStatusOrderByOrderIdDescAndDate(@Param("status") Integer status, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    Long countAllByStatus(Integer status);

    List<Order> findOrdersByStatus(Integer status);
}
