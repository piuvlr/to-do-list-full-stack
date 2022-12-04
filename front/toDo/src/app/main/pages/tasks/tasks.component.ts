import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TaskModel } from '../../models/task.model';
import { TasksService } from '../../services/tasks.service';
import { EditTaskComponent } from '../edit-task/edit-task.component';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
})
export class TasksComponent implements OnInit {

  taskInProgress: TaskModel[] = [];
  taskVencidas: TaskModel[] = [];
  taskCompleted: TaskModel[] = [];
  breakpoint: number = 3;

  constructor(private _taskService: TasksService, public dialog: MatDialog) { }

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

  updateViewDeleteTask(task: TaskModel) {
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

  completedTask(task: TaskModel): void {
    this._taskService.putEditTask(task.id).subscribe({
      next: (success) => {
        this.taskCompleted.push(success);
        const listTasks = task.statusTask === statusTask.PROGRESS ? this.taskInProgress : this.taskVencidas;

        const index = listTasks.findIndex(taskList => taskList.id === task.id)
        listTasks.splice(index, 1);
      }
    })
  }

  separeTaskForStatus(taskList: TaskModel[]) {
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
  }

  editTask(task: TaskModel) {
    const dialogRef = this.dialog.open(EditTaskComponent,
      {data: task})

      dialogRef.afterClosed().subscribe(
        () => {
          this.taskInProgress = []
          this.listTasks();
        }
      )
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
