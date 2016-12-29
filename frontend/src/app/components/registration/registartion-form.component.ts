import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../_services/authentication.service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";
import {EmailValidator} from "../../_validators/email-validator";
import {User} from "../../_mocks/username";

@Component({
  selector: 'registartion-form',
  templateUrl: 'registartion-form.component.html',
  styleUrls: ['registration-form.component.css']
})

export class Registration {
  errorMessage: string;
  succesMessage: string;
  registrationForm: FormGroup;
  username: User;

  constructor(private fb: FormBuilder,
              private router: Router,
              private authenticationService: AuthenticationService) {
    this.registrationForm = fb.group({
      'firstname': [null, [Validators.required, Validators.pattern('[A-Z][a-z]*')]],
      'lastname': [null, [Validators.required, Validators.pattern('[A-Z][a-z]*')]],
      'username': [null, [Validators.required, Validators.minLength(6), Validators.pattern('[A-Za-z0-9]{6,}')]],
      'password': [null, [Validators.required, Validators.minLength(6), Validators.maxLength(16), Validators.pattern('([a-zA-Z0-9]*[0-9]+[a-zA-Z0-9]*[A-Z]+[a-zA-Z0-9]*' +
        '|[a-zA-Z0-9]*[A-Z]+[a-zA-Z0-9]*[0-9]+[a-zA-Z0-9]*)$')]],
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

    this.username = new User(model.firstname, model.lastname, model.username, model.email, model.password);
    this.authenticationService.createNewUser(this.username).subscribe(
      data => {
        this.succesMessage = data.message || 'Rejestracja przebiegla pomyslnie';
        this.registrationForm.reset();
      },
      error => {
        this.errorMessage = <any>error;
        if (this.errorMessage == 'Username is taken') {
          this.registrationForm.controls['username'].reset();
          this.registrationForm.controls['password'].reset();
          this.registrationForm.controls['conpassword'].reset();
        }
      }
    );
  }
}
