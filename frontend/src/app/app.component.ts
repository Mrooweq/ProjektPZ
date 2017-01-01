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
  title = 'App works!';
  activeUser: User;
  _subscriptions:Subscription[] = [];

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
    this.activeUser = currentUser.user;
  }

  ngOnInit(): void {
    this._subscriptions.push( this.authenticationService.isLoggedIn().subscribe(loggedIn => {
      if (loggedIn)
        this.setCurrentUser();
    }));
  }

  ngOnDestroy() {
    this.logout();
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
