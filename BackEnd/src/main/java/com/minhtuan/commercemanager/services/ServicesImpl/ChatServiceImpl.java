package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ChatDTO;
import com.minhtuan.commercemanager.dto.ChatRequestDTO;
import com.minhtuan.commercemanager.maper.ChatMapper;
import com.minhtuan.commercemanager.model.Chat;
import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.repository.ChatRepository;
import com.minhtuan.commercemanager.repository.CustomerRepository;
import com.minhtuan.commercemanager.repository.EmployeeRepository;
import com.minhtuan.commercemanager.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public boolean save(ChatRequestDTO chatRequestDTO) {
        Chat chat = new Chat();
        chat.setMessage(chatRequestDTO.getMessage());
        chat.setTime(chatRequestDTO.getTime());
        if(!chatRequestDTO.getUserId().equals("")){
            Customer customer = customerRepository.findCustomerById(chatRequestDTO.getUserId());
            chat.setCustomerChat(customer);
        }
        if(!chatRequestDTO.getEmployeeId().equals("")){
            Employee employee = employeeRepository.getById(chatRequestDTO.getEmployeeId());
            chat.setEmployeeChat(employee);
        }
        if(!chatRequestDTO.getToUserId().equals("")){
            Customer customer1 = customerRepository.getById(chatRequestDTO.getToUserId());
            chat.setCustomerToChat(customer1);
        }
        chatRepository.save(chat);
        return true;
    }

    @Override
    public List<ChatDTO> getChatByUser(String userId) {
        Customer customer = customerRepository.findCustomerById(userId);
        List<Chat> chatList = chatRepository.findAllByCustomerChatOrCustomerToChat(customer, customer);
        List<ChatDTO> chatDTOList = chatList.stream().map(chat -> chatMapper.toDTO(chat)).collect(Collectors.toList());
        return chatDTOList;
    }

    @Override
    public List<String> getUserList() {
        List<String> getUserList = chatRepository.getUserList();
        return getUserList;
    }

    @Override
    public ChatDTO getMessageByUserId(String userId) {
        Chat chat = chatRepository.getMessageByUser(userId);
        ChatDTO chatDTO = chatMapper.toDTO(chat);
        //List<ChatDTO> chatDTOList = chatList.stream().map(chat -> chatMapper.toDTO(chat)).collect(Collectors.toList());
        return chatDTO;
    }
}
