import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule }   from './app-routing.module';

import { AppComponent } from './app.component';
import { AnimalComponent } from './animal/animal.component';
import { AnimalDetailComponent } from './animal/animal-detail.component';
import { AnimalService } from './animal/animal.service';
import {AnimalFormComponent} from "./animal/animal-form.component";
import {HttpService} from "./login/http.service";
import {LoginForm} from "./login/login-form.component";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    AnimalComponent,
    AnimalDetailComponent,
    AnimalFormComponent,
    LoginForm
  ],
  providers: [AnimalService,HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
