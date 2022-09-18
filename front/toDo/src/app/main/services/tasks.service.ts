import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

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
}
