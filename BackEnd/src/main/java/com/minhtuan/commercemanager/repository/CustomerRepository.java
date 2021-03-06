package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findAllByStatus(Integer status);
    Customer findCustomerById(String customerId);
    Long countAllByStatus(Integer status);
}
