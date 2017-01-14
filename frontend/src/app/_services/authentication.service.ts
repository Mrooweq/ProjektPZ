import {Injectable, OnInit} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';

import {Observable, Subject} from "rxjs";
import {User} from "../_mocks/user";

@Injectable()
export class AuthenticationService {
  private httpLoginUrl = 'api/login';
  private httpLogoutUrl = 'api/logout';
  private httpRegisterUrl = 'api/register';
  private loggedIn: boolean;
  private logger = new Subject<boolean>();

  constructor(private http: Http) {
    if (localStorage.getItem('currentUser'))
      this.loggedIn = true;
    else
      this.loggedIn = false;
  }

  requestOptions() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser && currentUser.tokenContainer.token) {
      let headers = new Headers({'Authorization': currentUser.tokenContainer.token});
      return new RequestOptions({headers: headers});
    }
  }

  isCurrentUser() {
    return this.loggedIn;
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

  public logout(username: String) {
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

  public logoutLocal() {
    localStorage.removeItem('currentUser');
    this.loggedIn = false;
    this.logger.next(this.loggedIn);

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
    } else if (error.status === 406) {
      errorMsg = error.message || `Email is alredy used`
    } else {
      errorMsg = error.message ||
        `Oops! Error status: ` + error.status
    }
    return Observable.throw(errorMsg);

  }
}
