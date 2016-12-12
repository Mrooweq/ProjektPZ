import {Component} from '@angular/core';
import { UserService } from '../_services/user.service';
import {Router} from "@angular/router";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'login',
  templateUrl: 'login-form.component.html',
  styleUrls: ['../_css/login-form.component.css']
})
export class LoginForm{
  errorMessage: string;
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private userService: UserService) {
    this.loginForm = fb.group({
      'username' : [null,Validators.required],
      'password' : [null,Validators.required]
      // 'password':  [null, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(10)])]
    })
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  submitForm(value: any):void{
    console.log('Reactive Form Data: ')
    console.log(value);
    this.loginForm.reset();
  }

  login(username: string, password: string):void{

    this.userService.login(username,password).subscribe(
      error =>  this.errorMessage = <any>error,
      () => this.goBack()
    );
  }
}
