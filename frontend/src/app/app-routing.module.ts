import {NgModule}             from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {LoginForm} from "./components/login/login-form.component";
import {Home} from "./components/home/home.component";
import {Registration} from "./components/registration/registartion-form.component";
import {SearchResults} from "./components/search_results/search-results.component";
import {UserAccount} from "./components/user_account/user_account.component";
import {AuthGuard} from "./_services/auth_guard.service";

const routes: Routes = [
  {path: '', component: Home},
  {path: 'login', component: LoginForm},
  {path: 'registration', component: Registration},
  {path: 'results', component: SearchResults},
  {path: 'account', component: UserAccount, canActivate: [AuthGuard]}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
