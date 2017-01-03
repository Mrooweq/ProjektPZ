import {Component, OnInit, OnDestroy} from '@angular/core';
import {AuthenticationService} from "./_services/authentication.service";
import {User} from "./_mocks/user";
import {Subscription} from "rxjs";

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['../_css/app.component.css']
})
export class AppComponent implements OnInit,OnDestroy {
  private title = 'MALINKI BOOKING';
  private activeUser: User;
  private _subscriptions: Subscription[] = [];

  constructor(private authenticationService: AuthenticationService) {
  }

  logout() {
    this._subscriptions.push(this.authenticationService.logout(this.activeUser.username).subscribe(
      () => {
        this.activeUser = null;
      },
      error => {
        console.log(error);
      }
    ));
  }

  setCurrentUser() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser)
      this.activeUser = currentUser.user;
    else
      this.activeUser = null;
  }

  ngOnInit(): void {
    this.setCurrentUser();
    this._subscriptions.push(this.authenticationService.isLoggedIn().subscribe(loggedIn => {
      if (loggedIn)
        this.setCurrentUser();
    }));
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
