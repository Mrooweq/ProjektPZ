import {Component, OnInit, OnDestroy} from '@angular/core';
import {FormGroup, FormBuilder} from "@angular/forms";
import {SearchService} from "../../_services/search.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'home',
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.css']
})
export class Home implements OnInit,OnDestroy {

  private searchForm: FormGroup;
  _sources: String[];
  _dest: String[];
  _classes: String[];
  _subscriptions: Subscription[] = [];


  myDatePickerOptions = {
    todayBtnTxt: 'Today',
    dateFormat: 'yyyy-mm-dd',
    firstDayOfWeek: 'mo',
    sunHighlight: true,
    inline: false,
    customPlaceholderTxt: 'yyyy-mm-dd',
    disableUntil: {year: 2015, month: 11, day: 1},
    selectionTxtFontSize: '14px'
  };

  constructor(private fb: FormBuilder,
              private router: Router,
              private searchService: SearchService) {
  }

  callback(value, name: String) {
    if (name == 'source')
      this.searchForm.controls['source'].setValue(value);
    if (name == 'destination')
      this.searchForm.controls['destination'].setValue(value);
  }

  submit(value: any) {
    this._subscriptions.push(this.searchService.getFlights(value).subscribe(
      flights => {
        this.router.navigate(['/results']);
      },
      error => {
        console.log(error);
      }
    ));
    this.searchForm.reset();
  }

  onDateChanged(value: any, name: String) {
    if (name == 'start')
      this.searchForm.controls['start'].setValue(value.formatted);
    if (name == 'end')
      this.searchForm.controls['end'].setValue(value.formatted);
  }

  ngOnInit(): void {
    this._subscriptions.push(this.searchService.getSourceAirport().subscribe(
      sources => {
        this._sources = sources;
      },
      error => {
        console.log(error);
      }
    ));

    this._subscriptions.push(this.searchService.getDestinationAirport().subscribe(
      dest => {
        this._dest = dest;
      },
      error => {
        console.log(error);
      }
    ));
    this._subscriptions.push(this.searchService.getClasses().subscribe(
      classes => {
        this._classes = classes;
      },
      error => {
        console.log(error);
      }
    ));

    this.searchForm = this.fb.group({
      source: [null],
      destination: [null],
      start: [null],
      end: [null],
      _class: [null],
      travelers: [null]
    });
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
