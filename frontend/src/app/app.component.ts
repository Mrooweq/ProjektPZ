import {Component, OnInit, OnDestroy, AfterViewInit} from '@angular/core';
import {AuthenticationService} from "./_services/authentication.service";
import {User} from "./_mocks/user";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['../_css/app.component.css']
})
export class AppComponent implements OnInit,OnDestroy,AfterViewInit {
  private title = 'MALINKI BOOKING';
  private activeUser: User;
  private _subscriptions: Subscription[] = [];

  constructor(private authenticationService: AuthenticationService,
              private router: Router) {
    if (JSON.parse(localStorage.getItem('currentUser'))) {
      this.isTokenValid();
    }
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

  loginUser() {
    if (this.authenticationService.isCurrentUser()) {
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.activeUser = currentUser.user;
    }

    this._subscriptions.push(this.authenticationService.isLoggedIn().subscribe(user => {
      this.activeUser = user;
    }));
  }

  isTokenValid() {
    this.authenticationService.tokenValidation().subscribe(
      () => {
        this.loginUser();
      },
      error => {
        if (error == 401)
          this.authenticationService.logoutLocal();
      }
    );
  }

  ngAfterViewInit(): void {
    setTimeout(() => {
      $('.preloader').hide();
      $('#main-bg').fadeOut('slow');
    }, 3000)
  }

  ngOnInit(): void {
    this.loginUser();
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
