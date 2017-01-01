import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Http, URLSearchParams} from "@angular/http";

@Injectable()
export class SearchService {
  private flightsUrl = 'api/flights';
  private srcUrl = 'api/src';
  private destUrl = 'api/dest';
  private classesUrl = 'api/classes';

  constructor(private http: Http) {
  }

  getSourceAirport(): Observable<String[]> {
    let params = new URLSearchParams();
    params.set('dest', '');
    return this.http.get(this.srcUrl, {search: params})
      .map(res => res.json())
      .catch(this.handleError);
  }

  getDestinationAirport(): Observable<String[]> {
    let params = new URLSearchParams();
    params.set('src', '');
    return this.http.get(this.destUrl, {search: params})
      .map(res => res.json())
      .catch(this.handleError);
  }

  getClasses(): Observable<String[]> {
    return this.http.get(this.classesUrl)
      .map(res => res.json())
      .catch(this.handleError);
  }

  getFlights(value: any): Observable<any[]> {
    let params = new URLSearchParams();
    params.set('dateStart', value.start);
    params.set('dateEnd', value.end);
    params.set('from', value.source);
    params.set('to', value.destination);
    params.set('_class', value.class);
    params.set('numberOfPassengers', value.travelers);

    return this.http.get(this.flightsUrl, {search: params})
      .map(res => res.json())
      .catch(this.handleError);
  }

  private handleError(error: any) {
    let errorMsg;
    errorMsg = error.message ||
      `Oops! Error status: ` + error.status;

    return Observable.throw(errorMsg);
  }
}
