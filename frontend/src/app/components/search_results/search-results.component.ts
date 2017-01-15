import {Component, OnInit, OnDestroy, AfterViewChecked, AfterViewInit} from '@angular/core';
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
export class SearchResults implements OnInit,OnDestroy,AfterViewChecked,AfterViewInit {
  // private _flights: Flight[] = new Flight('NieLot', 100, '2016-01-01', 1000, 'Warsaw', 'Geneva')];
  private _flights: Flight[];
  private _subscriptions: Subscription[] = [];
  private loggedUser: User;


  constructor(private searchService: SearchService,
              private ticketService: TicketService,
              private authenticationService: AuthenticationService) {
  }

  buyTicket(flight: Flight) {
    this._subscriptions.push(this.ticketService.buyTicket(flight, this.loggedUser).subscribe(
      () => {
        console.log('hi');
      },
      error => {
        console.log(error);
      }
    ))
  }

  ngAfterViewInit(): void {
    $(document).ready(() => {
      let width = $('.arrow').width();
      $('.arrow').height(0.2 * width);
    });

    $(window).resize(() => {
      let width = $('.arrow').width();
      $('.arrow').height(0.2 * width);
    });
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

    this._subscriptions.push(this.authenticationService.isLoggedIn().subscribe(user => {
      this.loggedUser = user;
    }));
    this._flights = [];
    this._subscriptions.push(this.searchService.flights().subscribe(flights => {
      this._flights = flights;
    }));
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
