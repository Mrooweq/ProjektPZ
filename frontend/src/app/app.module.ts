import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {ReactiveFormsModule} from '@angular/forms';

import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {AppRoutingModule}   from './app-routing.module';
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
import {TicketHistory} from "./components/user_account/ticket_history/ticket_history.component";
import {Ng2TableModule} from 'ng2-table/ng2-table';
import {EditAccount} from "./components/user_account/edit_account/edit_account.component";
import {Flights} from "./components/search_results/flights/flights.component";
import {TicketInfo} from "./components/search_results/ticket_info/tickets_info.component";


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    MyDatePickerModule,
    Ng2AutoCompleteModule,
    Ng2TableModule
  ],
  declarations: [
    AppComponent,
    LoginForm,
    Registration,
    Home,
    SearchResults,
    UserAccount,
    TicketHistory,
    EqualValidator,
    EditAccount,
    Flights,
    TicketInfo
  ],
  providers: [AuthenticationService, SearchService, TicketService, AuthGuard, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
