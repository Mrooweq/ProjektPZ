import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';

import {Observable, Subject} from "rxjs";
import {User} from "../_mocks/user";

@Injectable()
export class UserService {
  private httpLoginUrl = 'api/login';
  private httpLogoutUrl = 'api/logout';
  private httpRegisterUrl = 'api/register';
  private loggedIn = false;
  private logger = new Subject<boolean>();

  constructor(private http: Http) {
  }

  isLoggedIn(): Observable<boolean> {
    return this.logger.asObservable();
  }

  login(username: string, password: string) {
    let headers = new Headers();
    headers.append('Authorization', 'login');
    let body = JSON.stringify({'username': username, 'password': password});

    return this.http.post(this.httpLoginUrl, body, {headers: headers})
      .map(res => {
        let user = res.json();
        if (user && user.tokenContainer.token) {
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.loggedIn = true;
          this.logger.next(this.loggedIn);
        }
      }).catch(this.handleError);
  }

  logout(username: String) {
    let body = JSON.stringify({'username': username});

    return this.http.post(this.httpLogoutUrl, body)
      .map(res => {
        if (res.status === 200) {
          localStorage.removeItem('currentUser');
          this.loggedIn = false;
          this.logger.next(this.loggedIn);
        }
      })
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
