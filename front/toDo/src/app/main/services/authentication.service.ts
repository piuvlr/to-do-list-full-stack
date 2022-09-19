import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private _httpClient: HttpClient) { }

  postLogin(userName: string, password: string): Observable<any> {
    const body = {
      userName: userName,
      password: password
    }

    return this._httpClient.post<any>(`http://localhost:8080/user/login`, body);
  }

  registerUser(userName: string, token: any): void {
    localStorage.setItem('userName', userName);
    localStorage.setItem('token', token.token);
    localStorage.setItem('typeToken', token.typeRequest);
  }
}
