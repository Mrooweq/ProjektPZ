import {Component, OnDestroy, AfterViewInit} from '@angular/core';
import {AuthenticationService} from '../../_services/authentication.service';
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {PasswordValidator} from "../../_validators/password.validator";
import {UsernameValidator} from "../../_validators/username.validator";
declare var $: JQueryStatic;

@Component({
  selector: 'login',
  templateUrl: 'login-form.component.html',
  styleUrls: ['login-form.component.css']
})
export class LoginForm implements OnDestroy,AfterViewInit {
  errorMessage: string = null;
  loginForm: FormGroup;
  _subscriptions: Subscription[] = [];

  constructor(private fb: FormBuilder,
              private authenticationService: AuthenticationService) {
    this.loginForm = fb.group({
      'username': [null, [Validators.required, UsernameValidator.usernameValidator]],
      'password': [null, [Validators.required, PasswordValidator.passwordValidator]]
    });
  }

  ngAfterViewInit(): void {
    $('#myModal').on('hidden.bs.modal', () => {
      this.loginForm.reset();
      this.errorMessage = null;
    });
  }

  login(loginFormValue: any): void {
    this.errorMessage = null;
    this._subscriptions.push(this.authenticationService.login(loginFormValue.username, loginFormValue.password)
      .subscribe(
        () => {
          $('#myModal').modal('hide');
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
