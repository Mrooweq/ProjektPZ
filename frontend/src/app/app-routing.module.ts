import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {LoginForm} from "./login/login-form.component";
import {AppComponent} from "./app.component";
import {Home} from "./home/home.component";

const routes: Routes = [
  { path: 'login', component:LoginForm },
  { path: '', component: Home }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
