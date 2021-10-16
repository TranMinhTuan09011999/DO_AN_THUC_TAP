import { formatDate } from '@angular/common';
import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { delay } from 'rxjs/operators';
import { ChatMessage } from '../chat-message';
import { Chat } from '../model/chat';
import { ChatMessageDto } from '../model/chat-message-dto';
import { ChatRequest } from '../model/chat-request';
import { ChatbotService } from '../service/chatbot.service';
import { TokenStorageService } from '../service/token-storage.service';
import { WebSocketServiceService } from '../service/web-socket-service.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit, OnDestroy {

  public items: Array<ChatMessage> = [];
  public chats: Array<Chat> = [];
  message!: string;
  chatMessage!: string;
  out = document.getElementById("box");
  token = "";
  today = '';
  dataForm!: FormGroup;
  userIdChat = '';

  constructor(private fb: FormBuilder, private tokenStorageService: TokenStorageService, private chatbotService: ChatbotService, private sanitizer:DomSanitizer, public webSocketService: WebSocketServiceService) { }

  ngOnInit(): void {
    this.getChatUser();
    this.webSocketService.openWebSocket();
    this.items = this.chatbotService.getItems();
    
    console.log(this.out!.scrollHeight);
    console.log(this.out!.clientHeight);
    this.out!.scrollTop = this.out!.clientHeight - 0;
    console.log(this.out!.scrollTop);
    
  }

  getChatUser(){
    if(this.token != "{}"){
      this.token = this.tokenStorageService.getToken();
      const user = this.tokenStorageService.getUser();
      this.userIdChat = user.id;
      this.chatbotService.getMessageChatUser(this.token, user.id)
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

  sendMessage(){
    this.chatbotService.getMessage(this.chatMessage)
        .subscribe(
          async (data) => {
            console.log(data);
            this.chatbotService.addMessage(this.chatMessage,"me");
            this.chatMessage = '';
            this.items = this.chatbotService.getItems();
            setTimeout(() => {
              this.scroll();
            }, 1000); 
            setTimeout(() => {
              this.chatbotService.addMessage(data,"bot");    
              this.items = this.chatbotService.getItems(); 
              setTimeout(() => {
                this.scroll();
              }, 1000);
            }, 2000);  
          },
          error => {
            console.log(error);
          });
  }

  scroll(){
    var out = document.getElementById("box");
            var isScrolledToBottom = out!.scrollHeight - out!.clientHeight <= out!.scrollTop + 1;
            console.log(isScrolledToBottom)
            if(!isScrolledToBottom){
              out!.scrollTop = out!.scrollHeight - out!.clientHeight;
            }
  }

  checkLink(message: String): boolean{
    var str = message.substring(0,14);
    if(str == "localhost:4200"){
      return true
    }
    return false;
  }

  sanitize(url:string){
    console.log(url);
    // this.sanitizer.bypassSecurityTrustUrl(url);
    window.open(url, "_blank");
    // return this.sanitizer.bypassSecurityTrustUrl(url);
}

reloadPage(): void {
  window.location.reload();
}

getChat(){
  this.token = this.tokenStorageService.getToken();
}

//--------------- Socket -------------------
ngOnDestroy(): void {
  this.webSocketService.closeWebSocket();
}

sendMessage1() {
  this.token = this.tokenStorageService.getToken();
  console.log(this.token);
  if(this.token != "{}"){
    const user = this.tokenStorageService.getUser();
    let today= new Date();
    const chatRequest = new ChatRequest(this.chatMessage, user.id, '', '', today);

    this.chatbotService.addMessageChat(this.token, chatRequest)
        .subscribe(
          (data) => {
            this.getMessageChatBot();
          },
          error => {
            console.log(error);
          });
  }else{
    this.getMessageChatBot();
  }
  
}

  getMessageChatBot(){
    const user = this.tokenStorageService.getUser();
    let today= Date.now();
    const chatMessageDto = new ChatMessageDto('me', this.chatMessage, today);
    this.webSocketService.sendMessage(chatMessageDto);
    var chatMess = this.chatMessage;
    this.chatMessage = '';
    setTimeout(() => {
      this.scroll();
    }, 1000);
    this.chatbotService.getMessage(chatMess)
        .subscribe(
          (data) => {
            setTimeout(() => {
              const chatMessageDto = new ChatMessageDto('bot', data, today);
              this.webSocketService.sendMessage(chatMessageDto);
              setTimeout(() => {
                this.scroll();
              }, 1000);
              let today1= new Date();
              const chatRequest = new ChatRequest(data, '', user.id, 'BOT', today1);
              this.chatbotService.addMessageChat(this.token, chatRequest)
              .subscribe(
                (data) => {
                  
                },
                error => {
                  console.log(error);
                });
            }, 2000); 
          },
          error => {
            console.log(error);
          });
  }
}
