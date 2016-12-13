import {Component} from '@angular/core';
import {UserService} from '../_services/user.service';
import {Router} from "@angular/router";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'login',
  templateUrl: 'login-form.component.html',
  styleUrls: ['../_css/login-form.component.css']
})
export class LoginForm {
  errorMessage: string;
  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
              private router: Router,
              private userService: UserService) {
    this.loginForm = fb.group({
      'username': [null, Validators.required],
      'password': [null, Validators.required]
      // 'password':  [null, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(10)])]
    })
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  login(loginFormValue: any): void {
    this.userService.login(loginFormValue.username, loginFormValue.password)
      .subscribe(
        data => {
          console.log(data);
          this.loginForm.reset();
          this.goBack();
        },
        error => {
          this.errorMessage = error;
          this.loginForm.reset();
        });
  }

}
