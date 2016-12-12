import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../_services/user.service";

@Component({
  selector: 'singin-form',
  templateUrl: 'registartion-form.component.html',
  styleUrls: ['../_css/registration-form.component.css']
})
export class Registration {
  errorMessage: string;

  constructor(
    private router: Router,
    private userService: UserService) {
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  createNewUser(username: string, password: string):void{
    this.userService.login(username,password).subscribe(
      error =>  this.errorMessage = <any>error,
      ()=>this.goBack()
    );
  }
}
