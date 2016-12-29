import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "./_services/authentication.service";
import {User} from "./_mocks/username";

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['../_css/app.component.css']
})
export class AppComponent implements OnInit {
  title = 'App works!';
  activeUser: User;

  constructor(private authenticationService: AuthenticationService) {
  }

  logout() {
    this.authenticationService.logout(this.activeUser.username).subscribe(
      () => {
        this.activeUser = null;
      },
      error => {
        console.log(error);
      }
    );
  }

  setCurrentUser() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.activeUser = currentUser.username;
  }

  logoutPreviousUser() {
    if (localStorage.getItem('currentUser')) {
      this.setCurrentUser();
      this.logout();
    }
  }

  ngOnInit(): void {
    this.logoutPreviousUser();

    this.authenticationService.isLoggedIn().subscribe(loggedIn => {
      if (loggedIn)
        this.setCurrentUser();
    });
  }
}
