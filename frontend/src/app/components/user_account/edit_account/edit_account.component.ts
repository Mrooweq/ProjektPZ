import {Component, AfterViewInit} from '@angular/core';

@Component({
  selector: 'edit-account',
  templateUrl: 'edit_account.component.html'
})

export class EditAccount implements AfterViewInit {
  clicked() {
    $('.edit-content').slideToggle('slow');
    $('.btn-group-edit').toggleClass('dropup');
  }

  ngAfterViewInit(): void {
    $('.edit-content').hide();
    $('.btn-group-edit').toggleClass('dropup');
  }
}
