import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TaskModel } from '../../models/task';
import { TasksService } from '../../services/tasks.service';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.scss']
})
export class EditTaskComponent implements OnInit {

  editTaskForm!: FormGroup;

  constructor(public dialogRef: MatDialogRef<EditTaskComponent>,
    @Inject(MAT_DIALOG_DATA) public data: TaskModel,
    private _formBuilder: FormBuilder,
    private _taskService: TasksService) { }

  ngOnInit(): void {
    this.editTaskForm = this._formBuilder.group({
      id: [this.data.id],
      nameTask: [this.data.nameTask],
      description: [this.data.description],
      deadlineDate: [this.data.deadlineDate]
    })
  }

  editTask(): void {
    const data = this.editTaskForm.getRawValue();

    this._taskService.postEditTask(data).subscribe({
      next: () => {
        this.close();
      }
    })

  }

  close() {
    this.dialogRef.close();
  }

}
