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

  constructor(private _router: Router,
    private _logout: AuthenticationService) {}

  newTask(): void {
    this._router.navigateByUrl('/new-task')
  }

  logout(): void {
    this._logout.logout().subscribe({
      next: (success) => {
        localStorage.clear();
        this._router.navigateByUrl('/login')
      }
    });
  }
}
