import {Component, OnInit, OnDestroy} from '@angular/core';
import {Flight} from "../../_mocks/flight";
import {SearchService} from "../../_services/search.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'my-app',
  templateUrl: './search-results.component.html',
  styleUrls: ['search-results.component.css']
})
export class SearchResults implements OnInit,OnDestroy {
  private _flights: Flight[];
  private _subscriptions: Subscription[] = [];

  constructor(private searchService: SearchService) {
  }

  ngOnInit(): void {
    this._flights = [];
    this._subscriptions.push(this.searchService.flights().subscribe(flights => {
      this._flights = flights;
    }));
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
