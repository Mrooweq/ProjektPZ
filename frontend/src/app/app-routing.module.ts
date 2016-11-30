import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {LoginForm} from "./login/login-form.component";
import {Home} from "./home/home.component";
import {SignUp} from "./signUp/signup-form.component";

const routes: Routes = [
  { path: 'login', component:LoginForm },
  { path: '', component: Home },
  { path: 'singup', component: SignUp }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
