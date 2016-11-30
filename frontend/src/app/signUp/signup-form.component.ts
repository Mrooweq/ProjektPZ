import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../user/user.service";

@Component({
  selector: 'singin-form',
  templateUrl: 'signup-form.component.html',
  styleUrls: ['signup-form.component.css']
})
export class SignUp {
  errorMessage: string;

  constructor(
    private router: Router,
    private userService: UserService) {
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  createNewUser(username: string, password: string):void{
    this.userService.loginAuthorization(username,password).subscribe(
      error =>  this.errorMessage = <any>error,
      ()=>this.goBack()
    );
  }
}
