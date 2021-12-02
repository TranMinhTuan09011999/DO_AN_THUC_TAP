import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CountOrder } from '../response/count-order';
import { HotSeliingProductResponse } from '../response/hot-seliing-product-response';
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

  getStatisticByQuater(token: String, quater: number, year: number, quater1: number, year1: number): Observable<StatisticResponse[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<StatisticResponse[]>(API_URL + 'admin/statistic/quater/' + quater + "/" + year + "/" + quater1 + "/" + year1, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getStatisticByYear(token: String, year: number, year1: number): Observable<StatisticResponse[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<StatisticResponse[]>(API_URL + 'admin/statistic/year/' + year + "/" + year1, { headers: headers})
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

  getStatisticHotSelling(token: String, month: String): Observable<HotSeliingProductResponse[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<HotSeliingProductResponse[]>(API_URL + 'admin/statistic/hotSellingProduct/' + month , { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getStatisticHotSellingFromTo(token: String, monthFrom: String, monthTo: String): Observable<HotSeliingProductResponse[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<HotSeliingProductResponse[]>(API_URL + 'admin/statistic/hotSellingProduct/' + monthFrom + "/" + monthTo , { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getStatisticHotSellingByQuater(token: String, quater: number, year: number): Observable<HotSeliingProductResponse[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<HotSeliingProductResponse[]>(API_URL + 'admin/statistic/hotSellingProduct/quater/' + quater + "/" + year , { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getStatisticHotSellingByQuaterFromTo(token: String, quater: number, year: number, quater1: number, year1: number): Observable<HotSeliingProductResponse[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<HotSeliingProductResponse[]>(API_URL + 'admin/statistic/hotSellingProduct/quater/' + quater + "/" + year + "/" + quater1 + "/" + year1, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getStatisticHotSellingByYear(token: String, year: number): Observable<HotSeliingProductResponse[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<HotSeliingProductResponse[]>(API_URL + 'admin/statistic/hotSellingProduct/year/' + year , { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getStatisticHotSellingByYearFromTo(token: String, year: number, year1: number): Observable<HotSeliingProductResponse[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<HotSeliingProductResponse[]>(API_URL + 'admin/statistic/hotSellingProduct/year/' + year + "/" + year1, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getStatisticCountCustomerFromTo(token: String, monthFrom: String, monthTo: String): Observable<CountOrder[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<CountOrder[]>(API_URL + 'admin/statistic/countCustomer/month/' + monthFrom + "/" + monthTo , { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getStatisticCountCustomerQuaterFromTo(token: String, quater: number, year: number, quater1: number, year1: number): Observable<CountOrder[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<CountOrder[]>(API_URL + 'admin/statistic/countCustomer/quater/' + quater + "/" + year + "/" + quater1 + "/" + year1 , { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getStatisticCountCustomerByYearFromTo(token: String, year: number, year1: number): Observable<CountOrder[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<CountOrder[]>(API_URL + 'admin/statistic/countCustomer/year/' + year + "/" + year1, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  //Thống kê lợi nhuận
  //Theo tháng
  getProfitByMonth(token: String, monthFrom: String, monthTo: String): Observable<CountOrder[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<CountOrder[]>(API_URL + 'admin/statistic/profit/month/' + monthFrom + "/" + monthTo , { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }
  //Theo quý
  getProfitByQuater(token: String, quater: number, year: number, quater1: number, year1: number): Observable<CountOrder[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<CountOrder[]>(API_URL + 'admin/statistic/profit/quater/' + quater + "/" + year + "/" + quater1 + "/" + year1 , { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }
  //Theo năm
  getProfitByyear(token: String, year: number, year1: number): Observable<CountOrder[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<CountOrder[]>(API_URL + 'admin/statistic/profit/year/' + year + "/" + year1, { headers: headers})
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
