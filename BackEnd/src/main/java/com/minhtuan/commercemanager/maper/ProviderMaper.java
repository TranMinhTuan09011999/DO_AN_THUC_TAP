package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.model.Category;
import com.minhtuan.commercemanager.model.Provider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProviderMaper {
    public ProviderDTO toDTO(Provider provider){
        ProviderDTO dto = new ProviderDTO();
        dto.setProviderId(provider.getProviderId());
        dto.setProviderName(provider.getProviderName());
        dto.setProviderAddress(provider.getProviderAddress());
        dto.setProviderEmail(provider.getProviderEmail());
        dto.setProviderPhone(provider.getProviderPhone());
        dto.setStatus(provider.getStatus());
        return dto;
    }
}
