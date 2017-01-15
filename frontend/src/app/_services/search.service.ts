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

  private _flights: Flight[] = [];

  constructor(private http: Http) {
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

  getFlights(flight: any): Observable<Flight[]> {
    let params = new URLSearchParams();
    params.set('dateStart', flight.start);
    params.set('dateEnd', flight.end);
    params.set('from', flight.source);
    params.set('to', flight.destination);
    params.set('_class', flight._class);
    params.set('numberOfPassengers', flight.travelers);
    console.log(params);

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
