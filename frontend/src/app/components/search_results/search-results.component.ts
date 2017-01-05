import {Component, OnInit, OnDestroy, AfterViewChecked} from '@angular/core';
import {Flight} from "../../_mocks/flight";
import {SearchService} from "../../_services/search.service";
import {Subscription} from "rxjs";
import {User} from "../../_mocks/user";
import {AuthenticationService} from "../../_services/authentication.service";
import {TicketService} from "../../_services/tickets.service";
declare var $: JQueryStatic;

@Component({
  selector: 'my-app',
  templateUrl: './search-results.component.html',
  styleUrls: ['search-results.component.css']
})
export class SearchResults implements OnInit,OnDestroy,AfterViewChecked {
  private _flights: Flight[] = [new Flight('NieLot', 100, '2016-01-01', 1000, 'Warsaw', 'Geneva')];
  private _subscriptions: Subscription[] = [];
  private loggedUser: User;


  constructor(private searchService: SearchService,
              private ticketService: TicketService,
              private authenticationService: AuthenticationService) {
  }

  click(flight: Flight) {
    this._subscriptions.push(this.ticketService.buyTicket(flight, this.loggedUser).subscribe(
      () => {
        console.log('hi');
      },
      error => {
        console.log(error);
      }
    ))
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

  ngAfterViewChecked() {
    $('.log_in_button').click(function () {
      $('#myModal').modal('show');
    });
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
