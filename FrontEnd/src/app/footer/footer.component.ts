import { Component, OnInit } from '@angular/core';
import { ChatbotService } from '../service/chatbot.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  message!: string;

  constructor(private chatbotService: ChatbotService) { }

  ngOnInit(): void {
  }

  sendMessage(){
    this.message = "hello";
    this.chatbotService.getMessage(this.message)
        .subscribe(
          (data) => {
            console.log(data);
          },
          error => {
            console.log(error);
          });
  }
}
