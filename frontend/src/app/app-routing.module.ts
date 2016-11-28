import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AnimalComponent }  from './animal/animal.component';
import {AnimalDetailComponent} from "./animal/animal-detail.component";
import {AnimalFormComponent} from "./animal/animal-form.component";
import {AppComponent} from "./app.component";
import {LoginForm} from "./login/login-form.component";

const routes: Routes = [
  { path: 'animals', component: AnimalComponent },
  { path: 'animals/detail/:id', component: AnimalDetailComponent },
  { path: 'newAnimal', component: AnimalFormComponent },
  { path: 'login', component:LoginForm }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
