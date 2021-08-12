package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.maper.WarehouseReceiptMapper;
import com.minhtuan.commercemanager.message.request.WarehouseReceiptRequest;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptResponse;
import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.WarehouseReceipt;
import com.minhtuan.commercemanager.repository.EmployeeRepository;
import com.minhtuan.commercemanager.repository.WarehouseReceiptRepository;
import com.minhtuan.commercemanager.services.WarehouseReceiptService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseReceiptServiceImpl implements WarehouseReceiptService {

    @Autowired
    WarehouseReceiptRepository warehouseReceiptRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WarehouseReceiptMapper warehouseReceiptMapper;

    @Override
    public String save(WarehouseReceiptRequest warehouseReceiptRequest) {
        WarehouseReceipt warehouseReceipt = new WarehouseReceipt();
        List<WarehouseReceipt> warehouseReceiptList = warehouseReceiptRepository.findAll();
        String idNew = "";
        if(warehouseReceiptList.size() > 0){
            Integer end = warehouseReceiptList.size() - 1;
            String id = warehouseReceiptList.get(end).getWarehouseReceiptId();
            String IdInt = id.substring(2);
            String IdBegin = id.substring(0,2);
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
            idNew = "PN00001";
        }
        warehouseReceipt.setWarehouseReceiptId(idNew);
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        warehouseReceipt.setDateOfIssue(date);
        Employee employee = employeeRepository.getById(warehouseReceiptRequest.getEmployeeId());
        warehouseReceipt.setEmployee(employee);
        warehouseReceipt.setAmount(warehouseReceiptRequest.getAmount());
        warehouseReceiptRepository.save(warehouseReceipt);
        return warehouseReceipt.getWarehouseReceiptId();
    }

    @Override
    public List<WarehouseReceiptResponse> getAll() {
        List<WarehouseReceipt> warehouseReceiptList = warehouseReceiptRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<WarehouseReceiptResponse> warehouseReceiptResponseList = warehouseReceiptList
                .stream()
                .map(q -> warehouseReceiptMapper.toWarehouseReceiptResponse(q))
                .collect(Collectors.toList());
        return warehouseReceiptResponseList;
    }
}
