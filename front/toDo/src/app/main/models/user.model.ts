export class UserModel {
  constructor(
    public creationDate: Date,
    public emailPermissionsUserEnum: string,
    public emailUser: string,
    public id: number,
    public userName: string,
    public password: string
  ) {}
}
