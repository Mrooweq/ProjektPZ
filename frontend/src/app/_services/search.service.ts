import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Http, URLSearchParams} from "@angular/http";
import {Flight} from "../_mocks/flight";

@Injectable()
export class SearchService {
  private flightsUrl = 'api/flights';
  private srcUrl = 'api/src';
  private destUrl = 'api/dest';
  private classesUrl = 'api/classes';
  private _travelers: number;
  private _class: string;

  private _flights: Flight[] = [];

  constructor(private http: Http) {
  }

  travelers(): Observable<number>{
    return Observable.of(this._travelers);
  }

  class(): Observable<string>{
    return Observable.of(this._class);
  }

  flights(): Observable<Flight[]> {
    return Observable.of(this._flights);
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

  getFlights(flightValue: any): Observable<Flight[]> {
    let params = new URLSearchParams();
    params.set('dateStart', flightValue.start);
    params.set('dateEnd', flightValue.end);
    params.set('from', flightValue.source);
    params.set('to', flightValue.destination);
    params.set('_class', flightValue._class);
    params.set('numberOfPassengers', flightValue.travelers);
    this._class = flightValue._class;
    this._travelers = flightValue.travelers;

    return this.http.get(this.flightsUrl, {search: params})
      .map(res => {
          let flights = res.json();
          if (res.status === 200)
            this._flights = flights;
          return flights;
        }
      )
      .catch(this.handleError);
  }

  private handleError(error: any) {
    let errorMsg;
    errorMsg = error.message ||
      `Oops! Error status: ` + error.status;

    return Observable.throw(errorMsg);
  }
}
