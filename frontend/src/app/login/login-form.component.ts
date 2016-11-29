import {Component} from '@angular/core';
import { HttpService } from './http.service';
import {Router} from "@angular/router";
import {NgbTooltipConfig} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'login',
  templateUrl: 'login-form.component.html',
  styleUrls: ['login-form.component.css']
})
export class LoginForm{
  errorMessage: string;

  constructor(
    private router: Router,
    private httpService: HttpService,) {
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  login(username: string, password: string):void{
    this.httpService.loginAuthorization(username,password).subscribe(
      error =>  this.errorMessage = <any>error,
      ()=> this.router.navigate(['/login'])
    );
  }
}
