import {Injectable} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';

import {Observable} from "rxjs";

@Injectable()
export class HttpService {
  private httpUrl = 'api/animals';

  constructor(private http: Http) {
  }

  loginAuthorization(username:string,password:string){
    var headers = new Headers();
    headers.append('Authorization', 'login');
    let body = JSON.stringify({ "username":username,"password":password});

    return this.http.post(this.httpUrl, body , {headers: headers})
      .map(res => res.json())
      .catch(this.handleError);
  }

  private handleError(error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const data = error.json() || '';
      const err = data.error || JSON.stringify(data);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

}
