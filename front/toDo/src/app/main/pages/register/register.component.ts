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
      nameUser: [null],
      password: [null]
    })
  }

  login(): void {
    this._router.navigateByUrl('login');
  }

  postUser() {
    const register = this.formLogin.getRawValue();

    this.__authenticationService.postRegister(register.nameUser, register.password).subscribe({
      next: (success) => {
        this.__authenticationService.registerUser(register.nameUser, success);
        this._router.navigateByUrl('/tasks')
      },
      error: (e) => this.openSnackBarError()
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
