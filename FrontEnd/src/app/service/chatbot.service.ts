import { formatDate } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { ChatMessage } from '../chat-message';
import { Chat } from '../model/chat';
import { ChatRequest } from '../model/chat-request';

const API_URL = 'http://localhost:5000/chatbot/';
const API_URL_ = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ChatbotService {

  items!: ChatMessage | null;
  today = '';

  constructor(private http: HttpClient) { }

  getMessage(message: string): Observable<any> {
    return this.http.get(API_URL + message, {responseType: 'text'})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  addMessageChat(token: string, chatRequest: ChatRequest): Observable<any>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL_ + 'chatbot/addMessage', chatRequest, { headers: headers, responseType: 'text'})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getMessageChatUser(token: string, userId: string): Observable<Chat[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Chat[]>(API_URL_ + 'chatbot/chatUser/' + userId, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };

  addMessage(message: string, chatBy: String){
    let local_storage;
    let itemsInCart = []
    let today= Date.now();
    this.items = {
      message: message,
      chatBy: chatBy,
      dateTime: today
    }
    if(localStorage.getItem('chat')  == null){
      local_storage =[];
      console.log("LOCALSTORAGE NULL",JSON.parse(localStorage.getItem('chat')  || '{}'));
      itemsInCart.push(this.items);
      localStorage.setItem('chat', JSON.stringify(itemsInCart));
      console.log('Pushed first Item: ', itemsInCart);
    }else
    {
      local_storage = JSON.parse(localStorage.getItem('chat') || '{}');
      console.log("LOCAL STORAGE HAS ITEMS",JSON.parse(localStorage.getItem('chat') || '{}'));
      local_storage.forEach(function (item: any){
        itemsInCart.push(item);
      })
      if(this.items){
        itemsInCart.push(this.items);
      }
      localStorage.setItem('chat', JSON.stringify(itemsInCart));
    }
  }

  getItems(){
    console.log("Cart: ", JSON.parse(localStorage.getItem('cart') || '{}'));
    return this.items = JSON.parse(localStorage.getItem('chat') || '{}');
   }

  getMessUsers(token: string){
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Chat[]>(API_URL_ + 'chatbot/userList', { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }
}
