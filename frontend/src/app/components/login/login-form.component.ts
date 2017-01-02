import {Component, OnDestroy} from '@angular/core';
import {AuthenticationService} from '../../_services/authentication.service';
import {Router} from "@angular/router";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {Subscription} from "rxjs";

@Component({
  selector: 'login',
  templateUrl: 'login-form.component.html',
  styleUrls: ['login-form.component.css']
})
export class LoginForm implements OnDestroy{
  errorMessage: string;
  loginForm: FormGroup;
  _subscriptions: Subscription[] = [];

  constructor(private fb: FormBuilder,
              private authenticationService: AuthenticationService) {
    this.loginForm = fb.group({
      'username': [null, [Validators.required, Validators.pattern('[a-zA-Z0-9)]+')]],
      'password': [null, [Validators.required, Validators.pattern('[a-zA-Z0-9)]+')]]
    });
  }

  login(loginFormValue: any): void {
    this.errorMessage = null;
    this._subscriptions.push(this.authenticationService.login(loginFormValue.username, loginFormValue.password)
      .subscribe(
        () => {
          this.loginForm.reset();
        },
        error => {
          this.errorMessage = error;
          this.loginForm.reset();
        }));
  }
  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
