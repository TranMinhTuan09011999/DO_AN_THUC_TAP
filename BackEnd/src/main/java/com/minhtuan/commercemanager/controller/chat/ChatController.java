package com.minhtuan.commercemanager.controller.chat;

import com.minhtuan.commercemanager.dto.ChatDTO;
import com.minhtuan.commercemanager.dto.ChatRequestDTO;
import com.minhtuan.commercemanager.message.request.ReceiptRequest;
import com.minhtuan.commercemanager.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/chatbot")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/addMessage")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> addMessage(@RequestBody ChatRequestDTO chatRequestDTO){
        chatService.save(chatRequestDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/chatUser/{userId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> addMessage(@PathVariable(value = "userId") String userId){
        List<ChatDTO> chatDTOList = chatService.getChatByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(chatDTOList);
    }

    @GetMapping("/userList")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getUserList(){
        List<String> getUserList = chatService.getUserList();
        List<ChatDTO> chatDTOList = getUserList.stream().map(userId -> {
           return chatService.getMessageByUserId(userId);
        }).collect(Collectors.toList());
        chatDTOList.stream().sorted();
        List<ChatDTO> arrSort = chatDTOList.stream().sorted(Comparator.comparing(ChatDTO::getTime).reversed()).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(arrSort);
    }
}
