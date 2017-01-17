import {
  Component, Input, OnInit, OnDestroy, Output, EventEmitter,
  AfterViewChecked
} from '@angular/core';
import {Flight} from "../../../_mocks/flight";
import {AuthenticationService} from "../../../_services/authentication.service";
import {User} from "../../../_mocks/user";
import {Subscription} from "rxjs";
import {TicketService} from "../../../_services/tickets.service";
declare var $: JQueryStatic;

@Component({
  selector: 'flights',
  templateUrl: 'flights.component.html',
  styleUrls: ['flights.component.css']
})

export class Flights implements OnInit,OnDestroy,AfterViewChecked {
  @Input() flights: Flight[];
  @Output() isLink = new EventEmitter<boolean>();
  @Output() body = new EventEmitter<string>();
  private _subscriptions: Subscription[] = [];
  private loggedUser: User;

  constructor(private authenticationService: AuthenticationService,
              private ticketService: TicketService) {
    this.isLink.emit(false);
  }

  buyTicket(flight: Flight) {
    $('.buy_button').text('Loading...');
    $('.buy_button').attr('disabled', 'true');
    this._subscriptions.push(this.ticketService.buyTicket(flight, this.loggedUser).subscribe(
      data => {
        this.isLink.emit(true);
        this.body.emit(data.toString());
      },
      error => {
        $('.buy_button').text('Buy');
        $('.buy_button').attr('disabled', 'false');
        console.log(error);
        this.isLink.emit(false);
      }
    ))
  }

  loginUser() {
    if (this.authenticationService.isCurrentUser()) {
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.loggedUser = currentUser.user;
    }

    this._subscriptions.push(this.authenticationService.isLoggedIn().subscribe(user => {
      this.loggedUser = user;
    }));
  }

  ngAfterViewChecked() {
    $('.log_in_button').click(function () {
      $('#myModal').modal('show');
    });
  }

  ngOnInit(): void {
    this.loginUser();
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
