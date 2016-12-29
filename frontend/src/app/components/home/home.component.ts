import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder} from "@angular/forms";
import {SearchService} from "../../_services/search.service";

@Component({
  selector: 'home',
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.css']
})
export class Home implements OnInit {

  private searchForm: FormGroup;
  _sources: String[];
  _dest: String[];

  myDatePickerOptions = {
    todayBtnTxt: 'Today',
    dateFormat: 'yyyy-mm-dd',
    firstDayOfWeek: 'mo',
    sunHighlight: true,
    inline: false,
    customPlaceholderTxt: 'yyyy-mm-dd',
    disableUntil: {year: 2016, month: 11, day: 1},
    selectionTxtFontSize: '14px'
  };

  constructor(private fb: FormBuilder,
              private searchService: SearchService) {
  }

  callback(value, name: String) {
    if (name == 'source')
      this.searchForm.controls['source'].setValue(value);
    if (name == 'destination')
      this.searchForm.controls['destination'].setValue(value);
  }

  submit(value: any) {
    console.log(value);
    this.searchForm.reset();
  }

  onDateChanged(value: any, name: String) {
    if (name == 'start')
      this.searchForm.controls['start'].setValue(value.formatted);
    if (name == 'end')
      this.searchForm.controls['end'].setValue(value.formatted);
  }

  ngOnInit(): void {
    this.searchService.getSourceAirport().subscribe(
      sources => {
        this._sources = sources;
      },
      error => {
        console.log(error);
      }
    );

    this.searchService.getDestinationAirport().subscribe(
      dest => {
        this._dest = dest;
      },
      error => {
        console.log(error);
      }
    );
    this.searchForm = this.fb.group({
      source: [null],
      destination: [null],
      start: [null],
      end: [null],
      class: [null],
      travelers: [null]
    });
  }

}
