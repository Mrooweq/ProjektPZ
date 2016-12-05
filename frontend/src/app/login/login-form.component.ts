import {Component} from '@angular/core';
import { UserService } from '../user/user.service';
import {Router} from "@angular/router";

@Component({
  selector: 'login',
  templateUrl: 'login-form.component.html',
  styleUrls: ['login-form.component.css']
})
export class LoginForm{
  errorMessage: string;

  constructor(
    private router: Router,
    private userService: UserService) {
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  login(username: string, password: string):void{
    this.userService.loginAuthorization(username,password).subscribe(
      error =>  this.errorMessage = <any>error,
      ()=> this.router.navigate(['/login'])
    );
  }
}
