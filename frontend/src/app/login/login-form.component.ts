import {Component} from '@angular/core';
import { HttpService } from './http.service';
import {Router} from "@angular/router";

@Component({
  selector: 'login',
  templateUrl: 'login-form.component.html'
})
export class LoginForm {
  errorMessage: string;

  constructor(
    private router: Router,
    private httpService: HttpService) { }

  login(username: string, password: string):void{
    this.httpService.loginAuthorization(username,password).subscribe(
      error =>  this.errorMessage = <any>error,
      ()=> this.router.navigate(['/animals'])
    );
  }
}
