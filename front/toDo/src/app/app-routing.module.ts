import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TasksComponent } from './main/pages/tasks/tasks.component';

const routes: Routes = [
  {
    path: '', redirectTo: 'tasks', pathMatch: 'full'
  },
  {
    path: 'tasks', component: TasksComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
