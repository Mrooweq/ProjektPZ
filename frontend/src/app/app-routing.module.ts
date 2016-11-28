import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AnimalComponent }  from './animal/animal.component';
import {AnimalDetailComponent} from "./animal/animal-detail.component";
import {AnimalFormComponent} from "./animal/animal-form.component";

const routes: Routes = [
  { path: 'animals', component: AnimalComponent },
  { path: 'animals/detail/:id', component: AnimalDetailComponent },
  { path: 'newAnimal', component: AnimalFormComponent }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
