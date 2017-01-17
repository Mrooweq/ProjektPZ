import {Component, OnInit, OnDestroy, AfterViewInit} from '@angular/core';
import {Flight} from "../../_mocks/flight";
import {SearchService} from "../../_services/search.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

declare var $: JQueryStatic;

@Component({
  selector: 'my-app',
  templateUrl: './search-results.component.html',
  styleUrls: ['search-results.component.css']
})
export class SearchResults implements OnInit,OnDestroy,AfterViewInit {
  private _flights: Flight[];
  private _isLink: boolean = false;
  private body: string;
  private _subscriptions: Subscription[] = [];
  private travelers: number;
  private _class: string;

  constructor(private searchService: SearchService,
              private router: Router) {
    searchService.travelers().subscribe(
      travelers => {
        this.travelers = travelers;
      }
    );
    searchService.class().subscribe(
      _class => {
        this._class = _class;
      }
    );
  }

  goBack(): void {
    this.router.navigate(['/']);
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

  ngOnInit(): void {
    this._flights = [];
    this._subscriptions.push(this.searchService.flights().subscribe(flights => {
      for (let flight of flights) {
        this._flights.push(new Flight(flight.airlineName, flight.airlineShortcut, flight.price, flight.departureDate,
          flight.arrivalDate, flight.flightNumber, flight.from, flight.to, this._class, this.travelers));
      }
    }));
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
