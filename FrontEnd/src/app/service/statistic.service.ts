import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CountOrder } from '../response/count-order';
import { StatisticResponse } from '../response/statistic-response';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class StatisticService {

  constructor(private http: HttpClient) { }

  getStatistic(token: String, fromDate: string, toDate: string): Observable<StatisticResponse[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<StatisticResponse[]>(API_URL + 'admin/statistic/' + fromDate + "/" + toDate, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getCountOrderStatistic(token: String): Observable<CountOrder[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<CountOrder[]>(API_URL + 'admin/statistic/orderCountMonthly', { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getCountAccountStatistic(token: String): Observable<CountOrder[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<CountOrder[]>(API_URL + 'admin/statistic/accountCountMonthly', { headers: headers})
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
