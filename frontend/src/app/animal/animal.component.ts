import { Component} from '@angular/core';
import { AnimalService } from './animal.service';
import { Animal } from './animal'

@Component({
  selector: 'my-animal',
  templateUrl: './animal.component.html'
})
export class AnimalComponent {
  errorMessage: string;
  animals: Animal[];

  constructor(
    private animalService: AnimalService) { }

  getAnimals(): void {
    this.animalService.getAnimals().subscribe(
      animals => this.animals = animals,
      error => this.errorMessage = <any>error);
  }

  addAnimal(){
    this.animalService.addAnimal(3,'Kurczak',20)
      .subscribe(
        animal  => this.animals.push(animal),
        error =>  this.errorMessage = <any>error);
  }

  ngOnInit(): void {
    this.getAnimals();
  }
}
