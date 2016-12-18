import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { ReactiveFormsModule } from '@angular/forms';

import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { AppRoutingModule }   from './app-routing.module';

import { AppComponent } from './app.component';
import {UserService} from "./components/_services/user.service";
import {LoginForm} from "./components/login/login-form.component";
import {Home} from "./components/home/home.component";
import {Registration} from "./components/registration/registartion-form.component";
import {EqualValidator} from "./components/_validators/equal-validator.directive";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule,
    NgbModule.forRoot()
  ],
  declarations: [
    AppComponent,
    LoginForm,
    Registration,
    Home,
    EqualValidator
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
