import { Component, OnInit } from '@angular/core';
import { Animal } from './animal'

import {ActivatedRoute, Params, Router} from '@angular/router';
import { Location } from '@angular/common';
import {AnimalService} from "./animal.service";
import 'rxjs/add/operator/switchMap';


@Component({
  selector: 'my-animal-detail',
  templateUrl: 'animal-detail.component.html'
})

export class AnimalDetailComponent implements OnInit{

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private animalService: AnimalService
  ) {}

  animal: Animal;

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.animalService.update(this.animal).subscribe(
      ()=> this.goBack() );
  }

  ngOnInit():void {
    this.route.params
      .switchMap((params: Params) => this.animalService.getAnimal(+params['id']))
      .subscribe(animal => this.animal = animal);
  }
}
