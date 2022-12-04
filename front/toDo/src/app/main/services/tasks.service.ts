import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TaskModel } from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private httpClient: HttpClient) { }

  public getTakByUser(): Observable<any[]> {
    return this.httpClient.get<any[]>(`${environment.SERVER}/tasks`)
  }

  public deleteTask(id: number): Observable<boolean> {
    return this.httpClient.delete<boolean>(`${environment.SERVER}/tasks?id=${id}`)
  }

  public postTask(task: TaskModel): Observable<TaskModel> {
    return this.httpClient.post<TaskModel>(`${environment.SERVER}/tasks`, task)
  }

  public putEditTask(id: number): Observable<TaskModel> {
    return this.httpClient.put<TaskModel>(`${environment.SERVER}/tasks`,  id );
  }

  public postEditTask(task: TaskModel): Observable<TaskModel> {
    return this.httpClient.post<TaskModel>(`${environment.SERVER}/tasks/edit`,  task);
  }
}
