import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Color } from '../model/color';
import { ColorByProductIdAndSizeId } from '../model/color-by-product-id-and-size-id';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ColorService {

  constructor(private http: HttpClient) { }

  getColor(token: String): Observable<Color[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Color[]>(API_URL + 'admin/' + 'color', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getColorCustomer(): Observable<Color[]> {
    return this.http.get<Color[]>(API_URL + 'customer/' + 'color')
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getColorByColorId(token: String, colorId: number): Observable<Color> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Color>(API_URL + 'admin/' + 'color/' + colorId, { headers: headers})
                  .pipe( 
                    catchError(this.handleError));
  }

  getColorByProductIdAndSizeId(productId: string, sizeId: number, colorId: number): Observable<ColorByProductIdAndSizeId[]> {
    return this.http.get<ColorByProductIdAndSizeId[]>(API_URL + 'customer/color/' + productId + '/' + sizeId + '/' + colorId)
                  .pipe(
                    retry(3), 
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
