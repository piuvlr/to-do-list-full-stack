import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  formLogin!: FormGroup

  constructor(private _formBuilder: FormBuilder,
    private _authenticationService: AuthenticationService,
    private _router: Router,
    private _snackBar: MatSnackBar ) { }

  ngOnInit(): void {
    this.formLogin = this._formBuilder.group({
      nameUser: [null],
      password: [null]
    })
  }

  postLogin() {
    const login = this.formLogin.getRawValue();

    this._authenticationService.postLogin(login.nameUser, login.password).subscribe({
      next: (success) => {
        this._authenticationService.registerUser(login.nameUser, success);
        this._router.navigateByUrl('/tasks')
      },
      error: (e) => this.openSnackBarError()
    })
  }

  openSnackBarError(): void {
    this._snackBar.open('Senha ou usuario errados', 'Tentar de novo', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 2500,
    });
  }
}
