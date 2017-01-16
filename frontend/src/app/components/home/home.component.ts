import {Component, OnInit, OnDestroy, AfterViewInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {SearchService} from "../../_services/search.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'home',
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.css'],
  providers: [DatePipe]
})
export class Home implements OnInit,OnDestroy,AfterViewInit {
  private searchForm: FormGroup;
  _sources: String[] = [];
  _dest: String[] = [];
  _classes: String[];
  _subscriptions: Subscription[] = [];
  _today: String;
  _todayDate: Date;
  _tomorrow: String;
  myDatePickerOptions: any;

  constructor(private fb: FormBuilder,
              private router: Router,
              private searchService: SearchService,
              private datepipe: DatePipe) {

    this._todayDate = new Date();
    this._today = this.datepipe.transform(this._todayDate, 'yyyy-MM-dd');
    let tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    this._tomorrow = this.datepipe.transform(tomorrow, 'yyyy-MM-dd');

    this.myDatePickerOptions = {
      todayBtnTxt: 'Today',
      dateFormat: 'yyyy-mm-dd',
      firstDayOfWeek: 'mo',
      sunHighlight: true,
      inline: false,
      showClearDateBtn: false,
      showDateFormatPlaceholder: true,
      disableUntil: {
        year: this._todayDate.getFullYear(),
        month: (this._todayDate.getMonth() + 1),
        day: (this._todayDate.getDate() - 1)
      },
      selectionTxtFontSize: '14px'
    };
  }

  callback(value: any, name: String) {
    if (!value)
      value = '';
    else if (name == 'source')
      this.searchForm.controls['source'].setValue(value);
    else if (name == 'destination')
      this.searchForm.controls['destination'].setValue(value);
  }

  submit(value: any) {
    this._subscriptions.push(this.searchService.getFlights(value).subscribe(
      () => {
        this.router.navigate(['/results']);
      },
      error => {
        console.log(error);
        this.makeForm();
        this.searchForm.controls['_class'].setValue(this._classes[0]);
      }
    ));
  }

  makeForm() {
    this.searchForm = this.fb.group({
      source: [''],
      destination: [''],
      start: [this._today, Validators.required],
      end: [this._tomorrow, Validators.required],
      _class: ['', Validators.required],
      travelers: [1, Validators.required]
    });
  }

  onDateChanged(value: any, name: String) {
    if (name == 'start') {
      this.searchForm.controls['start'].setValue(value.formatted);
    }
    if (name == 'end') {
      this.searchForm.controls['end'].setValue(value.formatted);
    }
  }

  ngAfterViewInit(): void {
    $('#end').click(() => {
      let date = $('#start').text();
      console.log(date);

    })
  }

  ngOnInit(): void {
    this.makeForm();

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
        this.searchForm.controls['_class'].setValue(this._classes[0]);
      },
      error => {
        console.log(error);
      }
    ));
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
