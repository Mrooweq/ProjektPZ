import { Component } from '@angular/core';
import { AnimalService } from './animal.service';
import { Animal } from './animal'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AnimalService]
})
export class AppComponent {
  errorMessage: string;
  title = 'App works!';
  animals: Animal[];

  constructor(private animalService: AnimalService) { }

  getAnimals(): void {
    this.animalService.getAnimals().subscribe(
      animals => this.animals = animals,
      error => this.errorMessage = <any>error);
  }

  ngOnInit(): void {
    this.getAnimals();
  }
}
