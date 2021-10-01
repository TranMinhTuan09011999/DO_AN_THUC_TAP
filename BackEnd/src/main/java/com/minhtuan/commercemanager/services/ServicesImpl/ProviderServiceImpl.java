package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.maper.ProviderMaper;
import com.minhtuan.commercemanager.message.request.ProductRequest;
import com.minhtuan.commercemanager.message.request.ProviderRequest;
import com.minhtuan.commercemanager.model.Category;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.Provider;
import com.minhtuan.commercemanager.repository.ProviderRepository;
import com.minhtuan.commercemanager.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMaper providerMaper;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public ProviderDTO save(ProviderDTO providerRequest) {
        Provider provider = new Provider();
        List<Provider> providerList = providerRepository.findAll();
        String idNew = "";
        if(providerList.size() > 0){
            Integer end = providerList.size() - 1;
            String id = providerList.get(end).getProviderId();
            String IdInt = id.substring(3);
            String IdBegin = id.substring(0,3);
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
            idNew = "NCC00001";
        }
        provider.setProviderId(idNew);
        provider.setProviderName(providerRequest.getProviderName());
        provider.setProviderEmail(providerRequest.getProviderEmail());
        provider.setProviderAddress(providerRequest.getProviderAddress());
        provider.setProviderPhone(providerRequest.getProviderPhone());
        provider.setStatus(1);
        providerRepository.save(provider);
        ProviderDTO providerDTO = providerMaper.toDTO(provider);
        return providerDTO;
    }

    @Override
    public List<ProviderDTO> getAllProvider() {
        List<Provider> providerList = providerRepository.findAllByStatusOrderByProviderIdDesc(1);
        List<ProviderDTO> providerDTOList = providerList.stream().map(provider -> providerMaper.toDTO(provider)).collect(Collectors.toList());
        return providerDTOList;
    }

    @Override
    public ProviderDTO getProviderByProviderId(String providerId) {
        Provider Provider = providerRepository.getById(providerId);
        ProviderDTO providerDTO = providerMaper.toDTO(Provider);
        return providerDTO;
    }

    @Override
    public ProviderDTO updateProviderByProviderId(String providerId, ProviderRequest providerRequest) {
        Provider provider = providerRepository.findProviderByProviderId(providerId);
        provider.setProviderName(providerRequest.getProviderName());
        provider.setProviderAddress(providerRequest.getProviderAddress());
        provider.setProviderEmail(providerRequest.getProviderEmail());
        provider.setProviderPhone(providerRequest.getProviderPhone());
        providerRepository.save(provider);
        ProviderDTO providerDTO = providerMaper.toDTO(provider);
        return providerDTO;
    }

    @Override
    public void deleteProvider(String providerId) {
        Provider provider = providerRepository.getById(providerId);
        provider.setStatus(0);
        providerRepository.save(provider);
    }


}
