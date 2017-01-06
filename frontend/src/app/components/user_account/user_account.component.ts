import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../_services/authentication.service";
import {Subscription} from "rxjs";
import {User} from "../../_mocks/user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'user-account',
  templateUrl: 'user_account.component.html',
  styleUrls: ['user_account.component.css']
})

export class UserAccount {
  private errorMessage: string;
  private succesMessage: string;
  private editForm: FormGroup;
  private activeUser: User;
  private _subscriptions: Subscription[] = [];

  constructor(private router: Router,
              private fb: FormBuilder,
              private authenticationService: AuthenticationService) {
    this.editForm = fb.group({
      'firstname': [null, [Validators.pattern('[A-Z][a-z]*')]],
    });
  }

  goBack(): void {
    this.router.navigate(['/']);
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
    this.editForm = this.fb.group({
      'firstname': this.activeUser.firstname
    });
  }
}
