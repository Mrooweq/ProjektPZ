import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../_services/user.service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'registartion-form',
  templateUrl: 'registartion-form.component.html',
  styleUrls: ['registration-form.component.css']
})

export class Registration {
  errorMessage: string;
  succesMessage: string;
  registrationForm: FormGroup;

  constructor(private fb: FormBuilder,
              private router: Router,
              private userService: UserService) {
    this.registrationForm = fb.group({
      'firstname': ['', [Validators.required, Validators.pattern('[A-Z][a-z]*')]],
      'lastname': ['', [Validators.required, Validators.pattern('[A-Z][a-z]*')]],
      'username': ['', Validators.required],
      'password': ['', [Validators.required, Validators.minLength(6), Validators.maxLength(12)]],
      'conpassword': [''],
      'email': ['', Validators.required],
      'conemail': ['']
    })
  }

  goBack(): void {
    this.router.navigate(['/']);
  }

  createNewUser(username: string, password: string): void {
    this.userService.createNewUser(username, password).subscribe(
      () => {
        this.succesMessage = 'Rejestracja przebiegla pomyslnie';
      },
      error => {
        this.errorMessage = <any>error;
        this.registrationForm.reset();
      }
    );
  }

  onSubmit(value: Object) {
    console.log(value);
    this.registrationForm.reset();
  }
}
