import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserLogin } from '../model/userLogin';
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
}
