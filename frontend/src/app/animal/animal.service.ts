import {Injectable} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';

import {Animal} from './animal';
import {Observable} from "rxjs";

@Injectable()
export class AnimalService {
  private animalUrl = 'api/animals';
  private headers = new Headers({ 'Content-Type': 'application/json' });
  private options = new RequestOptions({ headers: this.headers });

  constructor(private http: Http) {
  }

  delete(id: number): Observable<void> {
    const url = `${this.animalUrl}/${id}`;

    return this.http.delete(url, this.options).map(() => null)
      .catch(this.handleError);
  }

  update(animal: Animal): Observable<Animal> {
    const url = `${this.animalUrl}/${animal.id}`;
    let animalString = JSON.stringify(animal);

    return this.http
      .put(url, animalString, this.options).map(() => animal)
      .catch(this.handleError);
  }

  addAnimal(id:number,name: string, age:number): Observable<Animal> {
    let animalString = JSON.stringify(new Animal(id,name,age));

    return this.http.post(this.animalUrl, animalString, this.options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  getAnimals(): Observable<Animal[]> {
    return this.http.get(this.animalUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }

  getAnimal(id: number): Promise<Animal> {
    return this.getAnimals().toPromise().then(animals => animals.find(animal => animal.id === id));
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
