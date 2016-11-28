import { Component, OnInit } from '@angular/core';
import { AnimalService } from './animal.service';
import { Animal } from './animal'
import {Router} from "@angular/router";

@Component({
  selector: 'my-animal-form',
  templateUrl: 'animal-form.component.html'
})

export class AnimalFormComponent implements OnInit{
  errorMessage: string;
  animals: Animal[];

  constructor(
    private animalService: AnimalService,
    private router: Router) { }

  getAnimals(): void {
    this.animalService.getAnimals().subscribe(
      animals => this.animals = animals,
      error => this.errorMessage = <any>error);
  }

  addAnimal(name: string,age: number){
    if (!name) { return; }
    let id = this.animals.length + 1;
    this.animalService.addAnimal(id,name,age)
      .subscribe(
        animal  => this.animals.push(animal),
        error =>  this.errorMessage = <any>error,
        ()=> this.router.navigate(['/animals']) );
  }

  ngOnInit(): void {
    this.getAnimals();
  }

}
