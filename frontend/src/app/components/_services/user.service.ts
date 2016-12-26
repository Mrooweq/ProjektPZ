import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';

import {Observable} from "rxjs";
import {User} from "../_mocks/user";

@Injectable()
export class UserService {
  private httpLoginUrl = 'api/login';
  private httpRegisterUrl = 'api/register';

  constructor(private http: Http) {
  }

  login(username: string, password: string) {
    let headers = new Headers();
    headers.append('Authorization', 'login');
    let body = JSON.stringify({'username': username, 'password': password});

    return this.http.post(this.httpLoginUrl, body, {headers: headers})
      .map(res => res.json())
      .catch(this.handleError);
  }

  createNewUser(user: User) {
    let headers = new Headers();
    headers.append('Authorization', 'register');
    let body = JSON.stringify(user);

    return this.http.post(this.httpRegisterUrl, body, {headers: headers})
      .catch(this.handleError);
  }


  private handleError(error: any) {
    let errorMsg;
    if (error.status === 500) {
      errorMsg = error.message ||
        error.status + `Internal Server Error`
    } else if (error.status === 403) {
      errorMsg = error.message || `Username or password is invalid`
    } else if (error.status === 409) {
      errorMsg = error.message || `Username is taken`
    } else {
      errorMsg = error.message ||
        `Oops! Error status: ` + error.status
    }
    return Observable.throw(errorMsg);

  }
}
