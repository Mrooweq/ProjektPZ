import {Component, OnInit} from '@angular/core';
import {UserService} from "./_services/user.service";
import {User} from "./_mocks/user";

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
    this.userService.logout(this.activeUser.username);
    this.activeUser = null;
  }

  ngOnInit(): void {
    if (localStorage.getItem('currentUser')) {
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      this.activeUser = currentUser.user;
      this.logout()
    }

    this.userService.isLoggedIn().subscribe(loggedIn => {
      if (loggedIn) {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.activeUser = currentUser.user;
      }
    });
  }
}
