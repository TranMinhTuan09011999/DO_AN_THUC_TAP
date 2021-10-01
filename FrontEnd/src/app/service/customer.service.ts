import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Customer } from '../model/customer';
import { AccountCustomerUpdate } from '../request/account-customer-update';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  getAllCustomer(token: String): Observable<Customer[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Customer[]>(API_URL + 'admin/customer/all', { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getCustomerById(token: String, customerId: string): Observable<Customer> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Customer>(API_URL + 'account/' + customerId, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  updateAccountCustomer(token: String, customerId: string, role: string,  accountCustomerUpdate: AccountCustomerUpdate): Observable<Customer> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<Customer>(API_URL + 'account/update/' + customerId + '/' + role, accountCustomerUpdate ,{ headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  deleteCustomer(token: String, customerId: string, status: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put(API_URL + 'admin/customer/status/' + customerId, {status} ,{ headers: headers, responseType: 'text'})
                  .pipe(
                    catchError(this.handleError));
  }

  getCustomerCount(token: string): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<any>(API_URL + 'admin/customer/count',{ headers: headers})
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
