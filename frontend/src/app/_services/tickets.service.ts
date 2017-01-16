import {Injectable, OnInit} from "@angular/core";
import {Observable} from "rxjs";
import {Http, Response, URLSearchParams} from "@angular/http";
import {Flight} from "../_mocks/flight";
import {User} from "../_mocks/user";
import {AuthenticationService} from "./authentication.service";

@Injectable()
export class TicketService {
  private buyTicketsUrl = 'api/buy';
  private getArchival = 'api/archival';

  private _archivalTickets: Flight[] = [];


  constructor(private http: Http,
              private authService: AuthenticationService) {
    this.authService = authService;
  }

  tickets(): Observable<Flight[]> {
    return Observable.of(this._archivalTickets);
  }

  buyTicket(flight: Flight, user: User) {
    let body = JSON.stringify({'flight': flight, 'user': user});

    return this.http.post(this.buyTicketsUrl, body, this.authService.requestOptions())
      .map((res: Response) => res.json().pdf)
      .catch(this.handleError.bind(this));
  }

  getArchivalTickets(): Observable<Flight[]> {
    let params = new URLSearchParams();
    let user = JSON.parse(localStorage.getItem('currentUser'));
    params.set('username', user.user.username);
    params.set('token', user.tokenContainer.token);

    return this.http.get(this.getArchival, {search: params})
      .map(res => {
          let ticket = res.json();
          if (res.status === 200)
            this._archivalTickets = ticket;
          return ticket;
        }
      )
      .catch(this.handleError);
  }

  private handleError(error: any) {
    let errorMsg;
    if (error.status === 401) {
      this.authService.logoutLocal();
    }
    errorMsg = error.message ||
      `Oops! Error status: ` + error.status;

    return Observable.throw(errorMsg);
  }
}
