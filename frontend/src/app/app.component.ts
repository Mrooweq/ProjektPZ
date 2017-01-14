import {Component, OnInit, OnDestroy} from '@angular/core';
import {AuthenticationService} from "./_services/authentication.service";
import {User} from "./_mocks/user";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['../_css/app.component.css']
})
export class AppComponent implements OnInit,OnDestroy {
  private title = 'MALINKI BOOKING';
  private activeUser: User;
  private _subscriptions: Subscription[] = [];

  constructor(private authenticationService: AuthenticationService,
              private router: Router) {
  }

  logout() {
    this._subscriptions.push(this.authenticationService.logout(this.activeUser.username).subscribe(
      () => {
        this.router.navigate(['/']);
      },
      error => {
        console.log(error);
      }
    ));
  }

  setUser(logged: any) {
    if (logged) {
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.activeUser = currentUser.user;
    }
    else {
      this.activeUser = null;
    }
  }

  ngOnInit(): void {
    if (this.authenticationService.isCurrentUser()) {
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.activeUser = currentUser.user;
    }

    this._subscriptions.push(this.authenticationService.isLoggedIn().subscribe(loggedIn => this.setUser(loggedIn)));
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
