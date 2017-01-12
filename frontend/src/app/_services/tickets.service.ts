import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Http, Headers, RequestOptions} from "@angular/http";
import {Flight} from "../_mocks/flight";
import {User} from "../_mocks/user";

@Injectable()
export class TicketService {
  private buyTickectsUrl = 'api/buy';

  constructor(private http: Http) {
  }

  buyTicket(flight: Flight, user: User) {
    let body = JSON.stringify({'flight': flight, 'user': user});
    console.log(body);

    return this.http.post(this.buyTickectsUrl, body, this.jwt())
      .map(res => res.json()).catch(this.handleError);
  }

  private jwt() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser && currentUser.tokenContainer.token) {
      let headers = new Headers({'Authorization': currentUser.tokenContainer.token});
      return new RequestOptions({headers: headers});
    }
  }

  private handleError(error: any) {
    let errorMsg;
    errorMsg = error.message ||
      `Oops! Error status: ` + error.status;

    return Observable.throw(errorMsg);
  }
}
