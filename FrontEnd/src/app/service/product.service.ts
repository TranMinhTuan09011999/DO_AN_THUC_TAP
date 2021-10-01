import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Product } from '../model/product';
import { ProductWithRoomIdAndCategoryId } from '../model/product-with-room-id-and-category-id';
import { ProductRequest } from '../request/product-request';
import { NewProductResponse } from '../response/new-product-response';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  createProduct(token: String, productRequest: ProductRequest): Observable<Product> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Product>(API_URL + 'admin/' + 'add-product', productRequest, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  updateProduct(token: String, productId: string, productRequest: ProductRequest): Observable<Product> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<Product>(API_URL + 'admin/product/update/' + productId, productRequest, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProduct(token: String): Observable<Product[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product[]>(API_URL + 'admin/' + 'product', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProductSearch(token: String, productId: string, productName: string, categoryId: string, providerId: string, status: number): Observable<Product[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    let params = new HttpParams();
    params = params.append('productId', productId);
    params = params.append('productName', productName);
    params = params.append('categoryId', categoryId);
    params = params.append('providerId', providerId);
    params = params.append('status', status.toString());
    return this.http.get<Product[]>(API_URL + 'admin/product/search', {params: params, headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProductByProductId(token: String, productId: string): Observable<Product> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Product>(API_URL + 'admin/product/' + productId, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProductWithRoomIdAndCategoryId(roomId: number, categoryId: string, orderBy: number): Observable<ProductWithRoomIdAndCategoryId[]> {
    return this.http.get<ProductWithRoomIdAndCategoryId[]>(API_URL + 'customer/productOderby/' + roomId + '/' + categoryId + '/' + orderBy)
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProductWithRoomIdAndCategoryIdAndColorId(roomId: number, categoryId: string, colorId: number): Observable<ProductWithRoomIdAndCategoryId[]> {
    return this.http.get<ProductWithRoomIdAndCategoryId[]>(API_URL + 'customer/getcolor/' + roomId + '/' + categoryId + '/' + colorId)
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProductWithRoomIdAndCategoryIdByPrice(roomId: number, categoryId: string, minValue: number, maxValue: number): Observable<ProductWithRoomIdAndCategoryId[]> {
    return this.http.get<ProductWithRoomIdAndCategoryId[]>(API_URL + 'customer/getPrice/' + roomId + '/' + categoryId + '/' + minValue + '/' + maxValue)
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getTop8NewProduct(roomId: number): Observable<ProductWithRoomIdAndCategoryId[]>{
    return this.http.get<ProductWithRoomIdAndCategoryId[]>(API_URL + 'customer/product/room/' + roomId)
                  .pipe(
                    catchError(this.handleError));
  }

  getTop8DiscountProduct(roomId: number): Observable<ProductWithRoomIdAndCategoryId[]>{
    return this.http.get<ProductWithRoomIdAndCategoryId[]>(API_URL + 'customer/product/room/discount/' + roomId)
                  .pipe(
                    catchError(this.handleError));
  }

  getNewProductCount(token: string): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<any>(API_URL + 'admin/product/count',{ headers: headers})
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
