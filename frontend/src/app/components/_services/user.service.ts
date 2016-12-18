import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';

import {Observable} from "rxjs";
import {User} from "../_mocks/user";

@Injectable()
export class UserService {
  private httpUrl = 'api/login';
  private loggedIn = false;

  constructor(private http: Http) {
  }

  login(login: string, password: string) {
    let headers = new Headers();
    headers.append('Authorization', 'login');
    let body = JSON.stringify({"login": login, "password": password});

    return this.http.post(this.httpUrl, body, {headers: headers})
      .map(res => res.json())
      .map((res) => {
        if (res.success) {
          this.loggedIn = true;
          console.log('logged in');
        }
        return res.success;
      })
      .catch(this.handleError);
  }

  createNewUser(user: User) {
    let headers = new Headers();
    headers.append('Authorization', 'register');
    let body = JSON.stringify(user);

    return this.http.post(this.httpUrl, body, {headers: headers})
      .map(res => res.json())
      .catch(this.handleError);
  }

  isLogged() {
    return this.loggedIn;
  }

  logout() {
    this.loggedIn = false;
  }

  private handleError(error: any) {
    let errorMsg;
    if (error.status === 500) {
      errorMsg = error.message ||
        error.status + ` Internal Server Error`
    } else {
      errorMsg = error.message ||
        error.status + `Oops!`
    }
    // throw an application level error
    return Observable.throw(errorMsg);
  }
}
