import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../_services/authentication.service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {EmailValidator} from "../../_validators/email.validator";
import {User} from "../../_mocks/user";
import {Subscription} from "rxjs";
import {PasswordValidator} from "../../_validators/password.validator";
import {UsernameValidator} from "../../_validators/username.validator";
import {FirstnameValidator} from "../../_validators/firstname.validator";
import {LastnameValidator} from "../../_validators/lastname.validator";

@Component({
  selector: 'registartion-form',
  templateUrl: 'registartion-form.component.html',
  styleUrls: ['registration-form.component.css']
})

export class Registration {
  private errorMessage: string;
  private succesMessage: string;
  private registrationForm: FormGroup;
  private user: User;
  private _subscriptions: Subscription[] = [];

  constructor(private fb: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService) {
    this.registrationForm = fb.group({
      'firstname': [null, [Validators.required, FirstnameValidator.firstnameValidator]],
      'lastname': [null, [Validators.required, LastnameValidator.lastnameValidator]],
      'username': [null, [Validators.required, Validators.minLength(3), UsernameValidator.usernameValidator]],
      'password': [null, [Validators.required, Validators.minLength(6), PasswordValidator.passwordValidator]],
      'conpassword': [null, Validators.required],
      'email': [null, [Validators.required, EmailValidator.emailValidator]],
      'conemail': [null, Validators.required]
    });
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  createNewUser(model: User): void {
    this.errorMessage = null;
    this.succesMessage = null;

    this.user = new User(model.firstname, model.lastname, model.username, model.email, model.password);
    this._subscriptions.push(this.authenticationService.createNewUser(this.user).subscribe(
      data => {
        this.succesMessage = data.message || 'Registration successful';
        this.registrationForm.reset();
        setTimeout(() => {
          this.router.navigate(['/']);
        }, 2000)
      },
      error => {
        this.errorMessage = <any>error;
        if (this.errorMessage == 'Username is taken') {
          this.registrationForm.controls['username'].reset();
          this.registrationForm.controls['password'].reset();
          this.registrationForm.controls['conpassword'].reset();
        } else if (this.errorMessage == 'Email is alredy used') {
          this.registrationForm.controls['email'].reset();
          this.registrationForm.controls['conemail'].reset();
          this.registrationForm.controls['password'].reset();
          this.registrationForm.controls['conpassword'].reset();
        } else {
          this.errorMessage = 'Upss! Somethings wrong';
        }
      }
    ));
  }

  ngOnDestroy() {
    this._subscriptions.forEach(s => s.unsubscribe());
  }
}
