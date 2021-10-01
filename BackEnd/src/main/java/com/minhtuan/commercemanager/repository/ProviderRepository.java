package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.model.Provider;
import com.minhtuan.commercemanager.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, String> {
    List<Provider> findAllByStatusOrderByProviderIdDesc(Integer status);
    Provider findProviderByProviderId(String providerId);
}
