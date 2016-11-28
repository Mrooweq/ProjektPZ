import { Component, OnInit} from '@angular/core';
import { AnimalService } from './animal.service';
import { Animal } from './animal'

@Component({
  selector: 'my-animal',
  templateUrl: 'animal.component.html'
})
export class AnimalComponent implements OnInit{
  errorMessage: string;
  animals: Animal[];

  constructor(
    private animalService: AnimalService) { }

  getAnimals(): void {
    this.animalService.getAnimals().subscribe(
      animals => this.animals = animals,
      error => this.errorMessage = <any>error);
  }

  delete(animal: Animal){
    this.animalService.delete(animal.id).subscribe(() => {
      this.animals = this.animals.filter(h => h !== animal);
    });
  }

  ngOnInit(): void {
    this.getAnimals();
  }
}
