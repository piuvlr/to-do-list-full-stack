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
    const token = localStorage.getItem('token')

    if (token !== null) {
      const authorization = 'Bearer ' + token;
      const authReq = req.clone({ setHeaders: { authorization } });
      return next.handle(authReq);
    } else {
      const authReq = req.clone();
      return next.handle(authReq);
    }

  }
}
