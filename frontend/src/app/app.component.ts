import {Component, OnInit} from '@angular/core';
import {UserService} from "./components/_services/user.service";
import {User} from "./components/_mocks/user";

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['../_css/app.component.css']
})
export class AppComponent implements OnInit {
  title = 'App works!';
  activeUser: User;

  constructor(private userService: UserService) {
  }

  logout() {
    this.userService.logout();
  }

  ngOnInit(): void {
    this.userService.logout();
    this.userService.isLoggedIn().subscribe(loggedIn => {
      if (loggedIn) {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.activeUser = currentUser.user;
      } else {
        this.activeUser = null;
      }
    });
  }
}
