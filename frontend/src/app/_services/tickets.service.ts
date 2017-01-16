import {Injectable, OnInit} from "@angular/core";
import {Observable} from "rxjs";
import {Http, Response, URLSearchParams, Headers} from "@angular/http";
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

  getArchivalTickets() {
    let body = JSON.stringify({'username': JSON.parse(localStorage.getItem('currentUser')).user.username});

    return this.http.post(this.getArchival, body, this.authService.requestOptions())
      .map(res => {
        let tickets = res.json();
        if (res.status === 200) {
          this._archivalTickets = tickets;
        }
        return tickets;
      })
      .catch(this.handleError.bind(this))
  }

  private
  handleError(error: any) {
    let errorMsg;
    if (error.status === 401)
      this.authService.logoutLocal();
    errorMsg = error.message ||
      `Oops! Error status: ` + error.status;
    return Observable.throw(errorMsg);
  }
}
