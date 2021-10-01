import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Category } from '../model/category';
import { CatogeryRequest } from '../request/catogery-request';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  public categoryId: string = "aaaa";

  // private categoryId = new BehaviorSubject("");
  // currentCategoryId = this.categoryId.asObservable();

  constructor(private http: HttpClient) { }

  // changeCategoryId(categoryId: string){
  //   this.categoryId.next(categoryId);
  // }

  createCategory(token: String, category: CatogeryRequest): Observable<Category> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Category>(API_URL + 'admin/' + 'add-category', category, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getCategory(token: String): Observable<Category[]> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Category[]>(API_URL + 'admin/' + 'category', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getCategoryByCategoryId(token: string, categoryId: string): Observable<Category> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Category>(API_URL + 'admin/category/' + categoryId, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getCategoryCustomer(): Observable<any> {
    return this.http.get<Category[]>(API_URL + 'customer/' + 'category')
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getCategoryByRoom(roomId: number): Observable<Category[]>{
    return this.http.get<Category[]>(API_URL + 'customer/category/' + roomId)
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  updateCategory(token: String, categoryId: string, catogeryRequest: CatogeryRequest): Observable<Category> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<Category>(API_URL + 'admin/category/update/' + categoryId, catogeryRequest, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  deleteCategory(token: String, categoryId: string): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put(API_URL + 'admin/category/delete/' + categoryId, {} ,{ headers: headers, responseType: 'text'})
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
