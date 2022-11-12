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
    const authorization = 'Bearer ' +  localStorage.getItem('token');
    const authReq = req.clone({ setHeaders: { authorization } });

    return next.handle(authReq);

  }
}
