import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Order } from '../model/order';
import { InvoiceRequest } from '../request/invoice-request';
import { OrderDetailRequest } from '../request/order-detail-request';
import { OrderRequest } from '../request/order-request';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  constructor(private http: HttpClient) { }

  addOrder(token: String, orderRequest : OrderRequest): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'order', orderRequest, { headers: headers, responseType: 'text'})
                  .pipe( 
                    catchError(this.handleError));
  }

  addOrderDetails(token: String, orderDetailRequest : OrderDetailRequest): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    console.log("kkkkkkk");
    return this.http.post(API_URL + 'orderDetail', orderDetailRequest, { headers: headers, responseType: 'text'})
                  .pipe( 
                    catchError(this.handleError));
  }

  addReceipt(token: String, invoiceRequest : InvoiceRequest): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'admin/invoice', invoiceRequest, { headers: headers, responseType: 'text'})
                  .pipe( 
                    catchError(this.handleError));
  }

  paymentPaypal(orderId: string, price: number): Observable<any>{
    let params = new HttpParams();
    params = params.append('price', price.toString());
    params = params.append('orderId', orderId);
    return this.http.post(API_URL + 'pay',{}, {params: params, responseType: 'text'})
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
}
