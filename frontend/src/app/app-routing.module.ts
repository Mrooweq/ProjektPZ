import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {LoginForm} from "./components/login/login-form.component";
import {Home} from "./components/home/home.component";
import {Registration} from "./components/registration/registartion-form.component";
import {SearchResults} from "./components/search_results/search-results.component";

const routes: Routes = [
  { path: '', component: Home },
  { path: 'login', component:LoginForm },
  { path: 'registration', component: Registration },
  { path: 'results', component: SearchResults }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
