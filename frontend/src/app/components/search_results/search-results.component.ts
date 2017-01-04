import {Component, OnInit, OnDestroy} from '@angular/core';
import {Flight} from "../../_mocks/flight";
import {SearchService} from "../../_services/search.service";
import {Subscription} from "rxjs";
import {AppComponent} from "../../app.component";
import {User} from "../../_mocks/user";
import {AuthenticationService} from "../../_services/authentication.service";

@Component({
  selector: 'my-app',
  templateUrl: './search-results.component.html',
  styleUrls: ['search-results.component.css']
})
export class SearchResults implements OnInit,OnDestroy {
  private _flights: Flight[] = [new Flight('NieLot', 100, '2016-01-01', 1000, 'Warsaw', 'Geneva')];
  private _subscriptions: Subscription[] = [];
  loggedUser: User;

  constructor(private searchService: SearchService,
              private authenticationService: AuthenticationService) {
  }

  click(value: Flight) {
    if (this.loggedUser)
      console.log(value.airline + "," + this.loggedUser.firstname);
  }

  setUser(logged: any) {
    if (logged) {
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.loggedUser = currentUser.user;
    }
    else {
      this.loggedUser = null;
    }
  }

  ngOnInit(): void {
    if (this.authenticationService.isCurrentUser()) {
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.loggedUser = currentUser.user;
    }
    this._subscriptions.push(this.authenticationService.isLoggedIn().subscribe(loggedIn => this.setUser(loggedIn)));

    this._flights = [];
    this._subscriptions.push(this.searchService.flights().subscribe(flights => {
      this._flights = flights;
    }));
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
