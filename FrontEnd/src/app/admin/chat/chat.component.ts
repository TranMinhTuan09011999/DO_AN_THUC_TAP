import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Chat } from 'src/app/model/chat';
import { ChatMessageDto } from 'src/app/model/chat-message-dto';
import { ChatRequest } from 'src/app/model/chat-request';
import { Customer } from 'src/app/model/customer';
import { ChatbotService } from 'src/app/service/chatbot.service';
import { CustomerService } from 'src/app/service/customer.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { WebSocketServiceService } from 'src/app/service/web-socket-service.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {

  chatMessage!: string;
  chatMessage1!: string;
  token = "";
  userId: string = '';
  customer!: Customer;
  public chats: Array<Chat> = [];
  employeeId: string='';

  constructor(private customerService: CustomerService, private chatbotService: ChatbotService, private route: ActivatedRoute, public webSocketService: WebSocketServiceService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.params['userId'];
    console.log(this.userId);
    this.getUserInfo();
    const employee = this.tokenStorageService.getUser();
    this.employeeId = employee.id;
    this.getChatUser();
    this.webSocketService.openWebSocket();
  }

  //--------------- Socket -------------------
  ngOnDestroy(): void {
    this.webSocketService.closeWebSocket();
  }

  // getChatUser(){
  //   if(this.token != "{}"){
  //     this.token = this.tokenStorageService.getToken();
  //     const user = this.tokenStorageService.getUser();
  //     this.userIdChat = user.id;
  //     this.chatbotService.getMessageChatUser(this.token, user.id)
  //           .subscribe(
  //             (data: Chat[]) => {
  //               console.log(data);
  //               this.chats = data;
  //             },
  //             error => {
  //               console.log(error);
  //             });
  //     }
  // }

  sendMessage() {
    this.token = this.tokenStorageService.getToken();
    const employee = this.tokenStorageService.getUser();
    let today1= new Date();
    const chatRequest = new ChatRequest(this.chatMessage, '', this.userId, employee.id, today1);
    console.log("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv")
    console.log(chatRequest)
    this.chatbotService.addMessageChat(this.token, chatRequest)
        .subscribe(
          (data) => {
            this.getMessageChatBot();
          },
          error => {
            console.log(error);
          });
  }

  getMessageChatBot(){
    let today= Date.now();
    const chatMessageDto = new ChatMessageDto(this.employeeId, this.chatMessage, today);
    this.webSocketService.sendMessage(chatMessageDto);
    this.chatMessage1 = this.chatMessage;
    this.chatMessage = '';
  }

  getUserInfo(){
    this.token = this.tokenStorageService.getToken();
    this.customerService.getCustomerById(this.token, this.userId)
        .subscribe(
          (data: Customer) => {
            console.log(data);
            this.customer = data;
          },
          error => {
            console.log(error);
          });
  }

  getChatUser(){
    this.token = this.tokenStorageService.getToken();
    this.chatbotService.getMessageChatUser(this.token, this.userId)
          .subscribe(
            (data: Chat[]) => {
              console.log(data);
               this.chats = data;
             },
            error => {
               console.log(error);
            });
  }
}
