import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatInputModule } from '@angular/material/input';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTabsModule } from '@angular/material/tabs';
import { LoginComponent } from './pages/login/login.component';
import { NewTaskComponent } from './pages/new-task/new-task.component';
import { RegisterComponent } from './pages/register/register.component';
import { TasksComponent } from './pages/tasks/tasks.component';
import { UtilsInterceptor } from './utils.interceptor';
import { MatBadgeModule } from '@angular/material/badge';
import { MatCheckboxModule} from '@angular/material/checkbox';





@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    TasksComponent,
    NewTaskComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    MatGridListModule,
    MatTabsModule,
    MatProgressBarModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSnackBarModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatBadgeModule,
    MatCheckboxModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: UtilsInterceptor, multi: true },
  ]
})
export class MainModule { }
