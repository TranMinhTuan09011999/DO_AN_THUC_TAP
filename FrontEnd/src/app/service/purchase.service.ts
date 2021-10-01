import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Order } from '../model/order';
import { OrderDetail } from '../model/order-detail';
import { OrderResponse } from '../response/order-response';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private http: HttpClient) { }

  getOrder(token: String, customerId: string): Observable<Order[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Order[]>(API_URL + 'order/all/' + customerId, { headers: headers})
                  .pipe( 
                    catchError(this.handleError));
  }

  getUnconfirmOrder(token: string, status: number): Observable<Order[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Order[]>(API_URL + 'admin/order/all/' + status, { headers: headers})
                  .pipe( 
                    catchError(this.handleError));
  }

  getUnconfirmOrderByCustomer(token: string, customerId: string, status: number): Observable<Order[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Order[]>(API_URL + 'order/all/' + customerId + '/' + status, { headers: headers})
                  .pipe( 
                    catchError(this.handleError));
  }

  getUnconfirmOrderByDate(token: string, status: number, fromDate: string, toDate: string): Observable<Order[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Order[]>(API_URL + 'admin/order/all/' + status + '/' + fromDate + '/' + toDate, { headers: headers})
                  .pipe( 
                    catchError(this.handleError));
  }

  getDeliveringOrder(token: string, status: number): Observable<OrderResponse[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<OrderResponse[]>(API_URL + 'admin/order/delivery/' + status, { headers: headers})
                  .pipe( 
                    catchError(this.handleError));
  }

  getDeliveringOrderByCustomer(token: string, customerId: string, status: number): Observable<OrderResponse[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<OrderResponse[]>(API_URL + 'order/delivery/' + customerId + '/' + status, { headers: headers})
                  .pipe( 
                    catchError(this.handleError));
  }

  getDeliveringOrderByDate(token: string, status: number, fromDate: string, toDate: string): Observable<OrderResponse[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<OrderResponse[]>(API_URL + 'admin/order/delivery/' + status + '/' + fromDate + '/' + toDate, { headers: headers})
                  .pipe( 
                    catchError(this.handleError));
  }

  cancel(token: String, id: string, status: number):Observable<Order>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<Order>(API_URL + 'order/status/' + id,{status},{headers: headers})
                    .pipe(
                      catchError(this.handleError)
                    );
  }

  payment(token: String, id: string, payment: number):Observable<Order>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<Order>(API_URL + 'order/payment/' + id,{payment},{headers: headers})
                    .pipe(
                      catchError(this.handleError)
                    );
  }

  getAllOrderDetailByOrderId(token: String, orderId: string): Observable<OrderDetail[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<OrderDetail[]>(API_URL + 'orderDetail/' + orderId, { headers: headers})
                  .pipe( 
                    catchError(this.handleError));
  }

  updateDeliveryOrder(token: String, orderId: string, employeeId: string, employeeIdDelivery: string):Observable<OrderResponse>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<OrderResponse>(API_URL + 'admin/order/update/' + orderId,{employeeId: employeeId, employeeIdDelivery: employeeIdDelivery},{headers: headers})
                    .pipe(
                      catchError(this.handleError)
                    );
  }

  getNewOrderCount(token: string): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<any>(API_URL + 'admin/order/count',{ headers: headers})
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
