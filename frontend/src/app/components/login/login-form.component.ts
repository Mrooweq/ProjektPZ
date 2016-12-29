import {Component} from '@angular/core';
import {AuthenticationService} from '../../_services/authentication.service';
import {Router} from "@angular/router";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'login',
  templateUrl: 'login-form.component.html',
  styleUrls: ['login-form.component.css']
})
export class LoginForm {
  errorMessage: string;
  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService) {
    this.loginForm = fb.group({
      'username': [null, [Validators.required, Validators.pattern('[a-zA-Z0-9)]+')]],
      'password': [null, [Validators.required, Validators.pattern('[a-zA-Z0-9)]+')]]
    });
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  login(loginFormValue: any): void {
    this.errorMessage = null;
    this.authenticationService.login(loginFormValue.username, loginFormValue.password)
      .subscribe(
        () => {
          this.loginForm.reset();
          this.goBack();
        },
        error => {
          this.errorMessage = error;
          this.loginForm.reset();
        });
  }

}
