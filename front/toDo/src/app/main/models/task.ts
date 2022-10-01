export class TaskModel {
  constructor(
    public id: number,
    public nameTask: string,
    public description: string,
    public statusTask: string,
    public creationDate: Date,
    public completedDate: Date,
    public deadlineDate: Date,
    public userName: String,) {}
}
