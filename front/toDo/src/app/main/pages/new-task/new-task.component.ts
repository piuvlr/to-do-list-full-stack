import { Router } from '@angular/router';
import { TasksService } from './../../services/tasks.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { TaskModel } from '../../models/task';

@Component({
  selector: 'app-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.scss']
})
export class NewTaskComponent implements OnInit {

  newTaskForm!: FormGroup;

  constructor(private _formBuilder: FormBuilder,
    private _tasksService: TasksService,
    private _router: Router) { }

  ngOnInit(): void {
    this.newTaskForm = this._formBuilder.group({
      nameTask: [null],
      description: [null],
      deadlineDate: [null]
    })
  }

  createTask() {
    const task: TaskModel = this.newTaskForm.getRawValue();

    console.log(this.newTaskForm.getRawValue())

    console.log(task)

    this._tasksService.postTask(task).subscribe({
      next: (success) => {
        this._router.navigateByUrl('tasks')
      }
    })
  }

  cancel() {
    this._router.navigateByUrl('tasks')
  }

}
