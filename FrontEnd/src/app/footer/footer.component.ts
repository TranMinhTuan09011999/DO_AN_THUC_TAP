import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { delay } from 'rxjs/operators';
import { ChatMessage } from '../chat-message';
import { ChatbotService } from '../service/chatbot.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  public items: Array<ChatMessage> = [];
  message!: string;
  chatMessage!: string;
  out = document.getElementById("box");

  constructor(private chatbotService: ChatbotService) { }

  ngOnInit(): void {
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
}
