import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder} from "@angular/forms";

@Component({
  selector: 'home',
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.css']
})
export class Home implements OnInit {

  private searchForm: FormGroup;
  cities = ['Warsaw', 'Kraków', 'Lublin', 'Wrocław'];

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

  constructor(private fb: FormBuilder) {
  }

  submit(value: any) {
    console.log(value);
    this.searchForm.reset();
  }

  onDateChanged(event: any) {
    this.searchForm.controls['depart'].setValue(event.formatted);
  }

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      source: [null],
      destination: [null],
      depart: [null],
      class: [null],
      travelers: [null]
    });
  }

}
