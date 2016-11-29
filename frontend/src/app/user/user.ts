export class User {
  username: string;
  password: string;


  constructor(login: string, password:string) {
    this.username = login;
    this.password = password;
  }
}
