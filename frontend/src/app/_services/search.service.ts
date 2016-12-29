import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Http, URLSearchParams} from "@angular/http";

@Injectable()
export class SearchService {
  private flightsUrl = 'api/flights';
  private srcUrl = 'api/src';
  private destUrl = 'api/dest';

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

  getTicket(){
    
  }

  private handleError(error: any) {
    let errorMsg;
    errorMsg = error.message ||
      `Oops! Error status: ` + error.status;

    return Observable.throw(errorMsg);
  }
}
