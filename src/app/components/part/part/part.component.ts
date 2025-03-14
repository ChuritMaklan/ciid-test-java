import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PartService } from '../../../services/part/part.service';
import { PersonService } from '../../../services/person/person.service';
import { CategoryService } from '../../../services/category/category.service';
import { Part } from '../../../models/part';
import { Person } from '../../../models/person';
import { Category } from '../../../models/category';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-part',
  standalone: true,
  templateUrl: './part.component.html',
  styleUrls: ['./part.component.css'],
  imports: [CommonModule, FormsModule],
})
export class PartComponent implements OnInit {
  parts: Part[] = [];
  persons: Person[] = [];
  categories: Category[] = [];
  name: string = '';
  price: number | null = null;
  quantity: number | null = null;
  description: string = '';
  personId: number | null = null;
  categoryIds: number[] = []; // Initialize as empty array

  constructor(
    private partService: PartService,
    private personService: PersonService,
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchParts();
    this.fetchPersons();
    this.fetchCategories();
  }

  fetchParts() {
    this.partService.getParts().subscribe({
      next: (data) => (this.parts= data),
      error: (err) =>{
        console.error('Error while fetching parts', err);
      },
    });
  }

  fetchPersons() {
    this.personService.getPersons().subscribe({
      next: (data) => (this.persons= data),
      error: (err) =>{
        console.error('Error while fetching persons', err);
      },
    });
  }

  fetchCategories() {
    this.categoryService.getCategories().subscribe({
      next: (data) => (this.categories= data),
      error: (err) =>{
        console.error('Error while fetching categories', err);
      },
    });
  }

  handleAddPart() {
    if (
      !this.name ||
      this.price === null ||
      this.quantity === null ||
      !this.description ||
      this.personId === null ||
      this.categoryIds.length === 0
    ) {
      alert('All fields are required');
      return;
    }
    console.log(this.categoryIds);
    this.partService
      .addPart({
        id: 0,
        name: this.name,
        price: this.price,
        quantityInStock: this.quantity,
        description: this.description,
        personId: this.personId,
        categoryIds: this.categoryIds,
      })
      .subscribe(() => {
        this.fetchParts();
        this.resetForm();
      });
  }

  handleUpdatePart(part: Part) {
    this.router.navigate(['/parts/update', part.id], { state: { part } });
  }

  handleDeletePart(id: number) {
    this.partService.deletePart(id).subscribe(() => {
      this.fetchParts();
    });
  }

  resetForm() {
    this.name = '';
    this.price = null;
    this.quantity = null;
    this.description = '';
    this.personId = null;
    this.categoryIds = []; // Reset to empty array
  }
}
