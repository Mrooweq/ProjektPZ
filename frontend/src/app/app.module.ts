import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {ReactiveFormsModule} from '@angular/forms';

import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {AppRoutingModule}   from './app-routing.module';
import {Ng2Bs3ModalModule} from "ng2-bs3-modal/ng2-bs3-modal";
import {MyDatePickerModule} from 'mydatepicker';
import {Ng2AutoCompleteModule} from 'ng2-auto-complete';

import {AppComponent} from './app.component';
import {AuthenticationService} from "./_services/authentication.service";
import {LoginForm} from "./components/login/login-form.component";
import {Home} from "./components/home/home.component";
import {Registration} from "./components/registration/registartion-form.component";
import {EqualValidator} from "./_validators/equal-validator.directive";
import {SearchService} from "./_services/search.service";
import {SearchResults} from "./components/search_results/search-results.component";
import {TicketService} from "./_services/tickets.service";
import {AuthGuard} from "./_services/auth_guard.service";
import {UserAccount} from "./components/user_account/user_account.component";
import {DatePipe} from "@angular/common";


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    Ng2Bs3ModalModule,
    MyDatePickerModule,
    Ng2AutoCompleteModule
  ],
  declarations: [
    AppComponent,
    LoginForm,
    Registration,
    Home,
    SearchResults,
    UserAccount,
    EqualValidator
  ],
  providers: [AuthenticationService, SearchService, TicketService, AuthGuard,DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
