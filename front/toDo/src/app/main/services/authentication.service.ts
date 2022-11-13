import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private _httpClient: HttpClient) { }

  private _loginSuccess = new Subject<boolean>();

  postLogin(userName: string, password: string): Observable<any> {
    const body = {
      userName: userName,
      password: password
    }

    return this._httpClient.post<any>(`${environment.SERVER}/user/login`, body);
  }

  registerUser(userName: string, token: any): void {
    localStorage.setItem('userName', userName);
    localStorage.setItem('token', token.token);
    localStorage.setItem('typeToken', token.typeRequest);
  }

  postRegister(register: any): Observable<any> {
    return this._httpClient.post<any>(`${environment.SERVER}/user/register`, register)
  }

  logout(): Observable<any> {
    return this._httpClient.post<any>(`${environment.SERVER}/user/logout`, null)
  }

  set isLogin(isLogin: boolean) {
    this._loginSuccess.next(isLogin);
  }

  get isLogin$(): Observable<boolean> {
    return this._loginSuccess.asObservable();
  }

}
