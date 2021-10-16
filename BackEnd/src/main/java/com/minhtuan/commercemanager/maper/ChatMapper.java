package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.ChatDTO;
import com.minhtuan.commercemanager.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ChatMapper {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public ChatDTO toDTO(Chat chat){
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setChatId(chat.getChatId());
        chatDTO.setMessage(chat.getMessage());
        if(Objects.nonNull(chat.getCustomerChat())){
            chatDTO.setCustomerChat(customerMapper.toDTO(chat.getCustomerChat()));
        }
        if(Objects.nonNull(chat.getEmployeeChat())){
            chatDTO.setEmployeeChat(employeeMapper.toDTO(chat.getEmployeeChat()));
        }
        if(Objects.nonNull(chat.getCustomerToChat())){
            chatDTO.setCustomerToChat(customerMapper.toDTO(chat.getCustomerToChat()));
        }
        chatDTO.setTime(chat.getTime());
        return chatDTO;
    }
}
