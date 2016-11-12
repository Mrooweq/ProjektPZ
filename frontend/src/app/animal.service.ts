import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import {Animal} from './animal';
import {Observable} from "rxjs";

@Injectable()
export class AnimalService {
  private url = 'api/animals'

  constructor(private http: Http) {
  }

  getAnimals(): Observable<Animal[]> {
    return this.http.get(this.url)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let data = res.json();
    return data || [];
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
