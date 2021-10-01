import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Provider } from '../model/provider';
import { ProviderRequest } from '../request/provider-request';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {

  constructor(private http: HttpClient) { }

  createProvider(token: String, provider: Provider): Observable<Provider> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<Provider>(API_URL + 'admin/' + 'add-provider', provider, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProvider(token: String): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Provider[]>(API_URL + 'admin/' + 'provider', { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getProviderByProviderId(token: String, providerId: string): Observable<Provider> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<Provider>(API_URL + 'admin/provider/' + providerId, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  updateProvider(token: String, providerId: string, providerRequest: ProviderRequest): Observable<Provider> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put<Provider>(API_URL + 'admin/provider/update/' + providerId, providerRequest, { headers: headers})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  deleteProvider(token: String, providerId: string): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.put(API_URL + 'admin/provider/delete/' + providerId, {} ,{ headers: headers, responseType: 'text'})
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
