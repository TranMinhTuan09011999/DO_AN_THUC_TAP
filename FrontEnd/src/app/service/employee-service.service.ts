import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Employee } from '../model/employee';
import { AccountCustomerUpdate } from '../request/account-customer-update';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class EmployeeServiceService {

  constructor(private http: HttpClient) { }

  getAllEmployee(token: String): Observable<Employee[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Employee[]>(API_URL + 'admin/employee/all', { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getEmployeeById(token: String, employeeId: string): Observable<Employee> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Employee>(API_URL + 'admin/employee/' + employeeId, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  updateAccountEmployee(token: String, customerId: string, role: string, accountCustomerUpdate: AccountCustomerUpdate): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<any>(API_URL + 'admin/account/update/' + customerId + '/' + role, accountCustomerUpdate ,{ headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getEmployeeCount(token: string): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<any>(API_URL + 'admin/employee/count',{ headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  deleteEmployee(token: String, employeeId: string, status: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put(API_URL + 'admin/employee/status/' + employeeId, {status} ,{ headers: headers, responseType: 'text'})
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
