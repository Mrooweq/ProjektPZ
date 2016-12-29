import {Injectable} from "@angular/core";
import {Subject, Observable} from "rxjs";
import {Http} from "@angular/http";

@Injectable()
export class UserService {
  constructor(private http: Http) {
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
