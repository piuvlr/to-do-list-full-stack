import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './main/services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  isLogin: boolean = localStorage.getItem('token') ? true : false;

  constructor(private _router: Router, private _authenticationService: AuthenticationService) {
    this._authenticationService.isLogin$.subscribe(
      (isLogin) => this.isLogin = isLogin
    )
  }

  newTask(): void {
    this._router.navigateByUrl('/new-task')
  }

  logout(): void {
    this._authenticationService.logout()
    this._router.navigateByUrl('/login')
    this._authenticationService.isLogin = false
  }
}
