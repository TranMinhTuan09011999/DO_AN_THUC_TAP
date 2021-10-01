package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.message.request.ProductRequest;
import com.minhtuan.commercemanager.message.request.ProviderRequest;

import java.util.List;

public interface ProviderService {
    public ProviderDTO save(ProviderDTO providerRequest);
    public List<ProviderDTO> getAllProvider();
    public ProviderDTO getProviderByProviderId(String providerId);
    public ProviderDTO updateProviderByProviderId(String providerId, ProviderRequest providerRequest);
    public void deleteProvider(String providerId);
}
