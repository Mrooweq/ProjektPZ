import {Component, OnInit} from '@angular/core';
import {Flight} from "../../_mocks/flight";
import {SearchService} from "../../_services/search.service";

@Component({
  selector: 'my-app',
  templateUrl: './search-results.component.html',
})
export class SearchResults implements OnInit {
  private _flights: Flight[];

  constructor(private searchService: SearchService) {
  }

  ngOnInit(): void {
    this._flights = [];
    this.searchService.flights().subscribe(flights => {
      this._flights = flights;
    });
  }
}
