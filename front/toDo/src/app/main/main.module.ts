import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { TasksComponent } from './pages/tasks/tasks.component';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider'
import { MatButtonModule } from '@angular/material/button'
import { MatGridListModule } from '@angular/material/grid-list'
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { UtilsInterceptor } from './utils.interceptor';

import {MatTabsModule} from '@angular/material/tabs';




@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    TasksComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    MatGridListModule,
    MatTabsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: UtilsInterceptor, multi: true }
  ]
})
export class MainModule { }
