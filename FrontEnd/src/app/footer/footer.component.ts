import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { delay } from 'rxjs/operators';
import { ChatMessage } from '../chat-message';
import { ChatMessageDto } from '../model/chat-message-dto';
import { ChatbotService } from '../service/chatbot.service';
import { WebSocketServiceService } from '../service/web-socket-service.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit, OnDestroy {

  public items: Array<ChatMessage> = [];
  message!: string;
  chatMessage!: string;
  out = document.getElementById("box");

  constructor(private chatbotService: ChatbotService, private sanitizer:DomSanitizer, public webSocketService: WebSocketServiceService) { }

  ngOnInit(): void {
    this.webSocketService.openWebSocket();
    this.items = this.chatbotService.getItems();
    
    console.log(this.out!.scrollHeight);
    console.log(this.out!.clientHeight);
    this.out!.scrollTop = this.out!.clientHeight - 0;
    console.log(this.out!.scrollTop);
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

//--------------- Socket -------------------
ngOnDestroy(): void {
  this.webSocketService.closeWebSocket();
}

sendMessage1() {
  const chatMessageDto = new ChatMessageDto('me', this.chatMessage);
  this.webSocketService.sendMessage(chatMessageDto);
  this.chatMessage = '';
}
}
