package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ProviderDTO;

import java.util.List;

public interface ProviderService {
    public ProviderDTO save(ProviderDTO providerRequest);
    public List<ProviderDTO> getAllProvider();
}
