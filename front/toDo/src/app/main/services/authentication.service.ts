import { UserModel } from './../models/user.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TokenModel } from './../models/token.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private _httpClient: HttpClient) { }

  private _loginSuccess = new Subject<boolean>();

  postLogin(userName: string, password: string): Observable<TokenModel> {
    const body = {
      userName: userName,
      password: password
    }

    return this._httpClient.post<TokenModel>(`${environment.SERVER}/user/login`, body);
  }

  registerUser(userName: string, token: TokenModel): void {
    localStorage.setItem('userName', userName);
    localStorage.setItem('token', token.token);
    localStorage.setItem('typeToken', token.typeRequest);
  }

  postRegister(register: UserModel): Observable<UserModel> {
    return this._httpClient.post<UserModel>(`${environment.SERVER}/user/register`, register)
  }

  logout(): boolean {
    localStorage.clear()
    return true;
  }

  set isLogin(isLogin: boolean) {
    this._loginSuccess.next(isLogin);
  }

  get isLogin$(): Observable<boolean> {
    return this._loginSuccess.asObservable();
  }

}
