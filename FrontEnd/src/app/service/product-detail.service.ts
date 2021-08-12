import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable, Provider } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { ProductDetail } from '../model/product-detail';
import { ProductWithRoomIdAndCategoryId } from '../model/product-with-room-id-and-category-id';
import { ProductRequest } from '../request/product-request';
import { CartResponse } from '../response/cart-response';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ProductDetailService {

  constructor(private http: HttpClient) { }

  createProductDetail(token: String, formData: FormData): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'admin/' + 'add-product-detail',formData, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProductDetail(token: String, productId: String): Observable<ProductDetail[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<ProductDetail[]>(API_URL + 'admin/product-detail/' + productId, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProducDetailWithProductIdAndColorIdAndSizeId(productId: string, colorId: number, sizeId: number): Observable<ProductWithRoomIdAndCategoryId[]> {
    return this.http.get<ProductWithRoomIdAndCategoryId[]>(API_URL + 'customer/' + productId + '/' + colorId + '/' + sizeId)
                  .pipe(
                    catchError(this.handleError));
  }

  getProductDetailTop1BySizeId(sizeId: number): Observable<ProductDetail[]>{
    return this.http.get<ProductDetail[]>(API_URL + 'customer/product/' + sizeId)
                  .pipe(
                    catchError(this.handleError));
  }

  getProductDetailByProductDetailId(productDetailId: number):Observable<CartResponse>{
    return this.http.get<CartResponse>(API_URL + 'customer/product-detail/' + productDetailId)
                  .pipe(
                    catchError(this.handleError));
  }

  ckeckProductExist(token: string, productId: string, sizeId: number, colorId: number): Observable<boolean> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<boolean>(API_URL + 'admin/' + productId + '/' + sizeId + '/' + colorId, { headers: headers})
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
