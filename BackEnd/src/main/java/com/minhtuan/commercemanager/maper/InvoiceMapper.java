package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.InvoiceDTO;
import com.minhtuan.commercemanager.model.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {
    public InvoiceDTO toDTO(Invoice invoice){
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setDate(invoice.getDate());
        invoiceDTO.setAmount(invoice.getAmount());
        return invoiceDTO;
    }
}
