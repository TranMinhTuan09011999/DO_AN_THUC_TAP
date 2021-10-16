package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ChatDTO;
import com.minhtuan.commercemanager.dto.ChatRequestDTO;

import java.util.List;

public interface ChatService {
    public boolean save(ChatRequestDTO chatRequestDTO);
    public List<ChatDTO> getChatByUser(String userId);
    public List<String> getUserList();
    public ChatDTO getMessageByUserId(String userId);
}
