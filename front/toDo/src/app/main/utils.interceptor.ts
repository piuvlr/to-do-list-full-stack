import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class UtilsInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authorization = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgLSBUQVNLUyIsInN1YiI6IjEiLCJpYXQiOjE2NjM1MTIxNzQsImV4cCI6MTY2MzU5ODU3NH0.Sb-VeJ1qGmXqN2W3giY0nvoEhChK--HqRYuok7GZKvM';
    const authReq = req.clone({ setHeaders: { authorization } });
    return next.handle(authReq);
  }
}
