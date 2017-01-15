import {Component, NgModule} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'user-account',
  templateUrl: 'user_account.component.html',
  styleUrls: ['user_account.component.css']
})

export class UserAccount {
  constructor(private router: Router) {
  }

  goBack(): void {
    this.router.navigate(['/']);
  }
}
