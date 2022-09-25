import { Component, OnInit } from '@angular/core';
import { TasksService } from '../../services/tasks.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
})
export class TasksComponent implements OnInit {

  taskInProgress: any[] = [];
  taskVencidas: any[] = [];
  taskCompleted: any[] = [];
  breakpoint: number = 3;

  constructor(private _taskService: TasksService) { }

  ngOnInit(): void {
    this.responsiveGrid();

    this.listTasks();
  }

  listTasks() {
    this._taskService.getTakByUser().subscribe({
      next: (success) => this.separeTaskForStatus(success),
      error: (error) => console.error(error)
    })
  }

  deleteTask(task: any) {
    this._taskService.deleteTask(task.id).subscribe({
      next: (success) => {
        if (success) {
          this.updateViewDeleteTask(task)
        }
      },
      error: (error) => console.error(error)
    })
  }

  updateViewDeleteTask(task: any) {
    if (task.statusTask === statusTask.PROGRESS) {
      const index = this.taskInProgress.findIndex(task => task.id === task.id);
      this.taskInProgress.splice(index, 1)
    } else if (task.statusTask === statusTask.DEADLINE) {
      const index = this.taskVencidas.findIndex(task => task.id === task.id);
      this.taskVencidas.splice(index, 1)
    } else if (task.statusTask === statusTask.COMPLETED) {
      const index = this.taskCompleted.findIndex(task => task.id === task.id);
      this.taskCompleted.splice(index, 1)
    }
  }

  separeTaskForStatus(taskList: any[]) {
    taskList.forEach(
      (task) => {
        if (task.statusTask === statusTask.PROGRESS) {
          this.taskInProgress.push(task);
        } else if (task.statusTask === statusTask.DEADLINE) {
          this.taskVencidas.push(task);
        } else if (task.statusTask === statusTask.COMPLETED) {
          this.taskCompleted.push(task);
        }
      }
    )
    console.log(this.taskInProgress)
  }

  private responsiveGrid(): void {
    if (window.innerWidth >= 1000) {
      this.breakpoint = 3;
    } else if (window.innerWidth >= 700) {
      this.breakpoint = 2;
    } else {
      this.breakpoint = 1;
    }
  }
}

enum statusTask {
  COMPLETED = "COMPLETED",
	PROGRESS = "PROGRESS",
	DEADLINE = "DEADLINE"
}
