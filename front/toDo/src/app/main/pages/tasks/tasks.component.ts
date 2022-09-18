import { Component, OnInit } from '@angular/core';
import { TasksService } from '../../services/tasks.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {
  taskInProgress: any[] = [];
  taskVencidas: any[] = [];
  taskCompleted: any[] = [];

  constructor(private _taskService: TasksService) { }

  ngOnInit(): void {
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
    debugger;
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
}

enum statusTask {
  COMPLETED = "COMPLETED",
	PROGRESS = "PROGRESS",
	DEADLINE = "DEADLINE"
}
