import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { UserLogin } from '../model/userLogin';
import { MessageResponse } from '../response/message-response';
import { TokenStorageService } from './token-storage.service';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user!: UserLogin;

  constructor(private http: HttpClient,
              private tokenStorageService: TokenStorageService) { }

  isAdmin() {
    if (this.tokenStorageService.getToken() == '{}' ) {
      return false;
    }
    else {
      if (this.tokenStorageService.getUser() != null) {
        this.user = this.tokenStorageService.getUser();
      }
      if (this.user.role === "ROLE_ADMIN" || this.user.role === "ROLE_EMPLOYEE") {
        return true;
      }
      return false;
    }
  }

  login(credentials: any): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      email: credentials.email,
      password: credentials.password,
      recaptchaResponse: credentials.recaptchaResponse
    }, httpOptions);
  }

  register(user: any, role: String): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      email: user.email,
      password: user.password,
      birthday: user.birthday,
      firstname: user.firstname,
      lastname: user.lastname,
      phone: user.phone,
      address: user.address,
      gender: user.gender,
      role: role
    }, httpOptions);
  }

  doesEmployeeExist(employeeId: string): Observable<boolean> {
    let url = `${AUTH_API}employeeCheck`;

    let content: any = {};
    content.employeeId = employeeId;

    let response$: Observable<boolean> = this.http.post<boolean>(url, content);

    return response$;
  }

  sendLink(email: string): Observable<MessageResponse>{
    return this.http.post<MessageResponse>(AUTH_API + 'forgot/' + email, {})
    .pipe(
      retry(3), 
      catchError(this.handleError));
  }

  reset(token: string, password: string): Observable<MessageResponse>{
    let params = new HttpParams();
    params = params.append('token', token);
    params = params.append('password', password);
    return this.http.post<MessageResponse>(AUTH_API + 'reset',{},{params: params})
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
