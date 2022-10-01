import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TaskModel } from '../models/task';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private httpClient: HttpClient) { }

  public getTakByUser(): Observable<any[]> {
    return this.httpClient.get<any[]>(`http://localhost:8080/tasks`)
  }

  public deleteTask(id: number): Observable<boolean> {
    return this.httpClient.delete<boolean>(`http://localhost:8080/tasks?id=${id}`)
  }

  public postTask(task: TaskModel): Observable<TaskModel> {
    return this.httpClient.post<TaskModel>(`http://localhost:8080/tasks`, task)
  }

  public putEditTask(id: number): Observable<TaskModel> {
    return this.httpClient.put<TaskModel>(`http://localhost:8080/tasks`,  id );
  }
}
