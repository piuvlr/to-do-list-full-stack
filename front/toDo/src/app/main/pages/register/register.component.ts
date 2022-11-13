import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  formLogin!: FormGroup;

  constructor(private _router: Router,
    private _formBuilder: FormBuilder,
    private __authenticationService: AuthenticationService,
    private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.formLogin = this._formBuilder.group({
      userName: [null],
      password: [null],
      emailUser: [null],
      emailPermissionsUserEnum: [false]
    })
  }

  login(): void {
    this._router.navigateByUrl('login');
  }

  postUser() {
    const register = this.formLogin.getRawValue();

    register.emailPermissionsUserEnum = register.emailPermissionsUserEnum ? "APPROVED" : "DENIED";

    this.__authenticationService.postRegister(register).subscribe({
      next: (success) => {
        this.postLogin(register);
      },
      error: (e) => this.openSnackBarError()
    })

  }

  postLogin(login: {userName: string, password: string}) {
    this.__authenticationService.postLogin(login.userName, login.password).subscribe({
      next: (success) => {
        this.__authenticationService.registerUser(login.userName, success)
        this._router.navigateByUrl('/tasks')
        this.__authenticationService.isLogin = true
      },
      error: e => console.log(e)
    })
  }

  openSnackBarError(): void {
    this._snackBar.open('Ocorreu algum erro ao se registrar', 'Tentar de novo', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 2500,
    });
  }

}
