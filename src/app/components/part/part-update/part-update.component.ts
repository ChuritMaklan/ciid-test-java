import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PartService } from '../../../services/part/part.service';
import { PersonService } from '../../../services/person/person.service';
import { CategoryService } from '../../../services/category/category.service';
import { Part } from '../../../models/part';
import { Person } from '../../../models/person';
import { Category } from '../../../models/category';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-update-part',
  standalone: true,
  templateUrl: './part-update.component.html',
  styleUrls: ['./part-update.component.css'],
  imports: [FormsModule, CommonModule]
})
export class UpdatePartComponent implements OnInit {
  part: Part = { id: 0, name: '', price: 0, quantityInStock: 0, description: '', personId: 0, categoryIds: []};
  persons: Person[] = [];
  categories: Category[] = [];

  constructor(
    private route: ActivatedRoute,
    private partService: PartService,
    private personService: PersonService,
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const partState = history.state.part;
    if (partState) {
      this.part = { ...partState };
    }
    this.fetchPersons();
    this.fetchCategories();
  }

  fetchPersons() {
    this.personService.getPersons().subscribe((data) => {
      this.persons = data;
    });
  }

  fetchCategories() {
    this.categoryService.getCategories().subscribe((data) => {
      this.categories = data;
    });
  }

  handleCategoryChange(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    const selectedOptions = Array.from(selectElement.selectedOptions);
    console.log('Selected Options:', selectedOptions);
  
    // Extract IDs from values like '1 : 3'
    const selectedIds = selectedOptions
      .map((option) => {
        const valueParts = option.value.split(': '); 
        console.log("valuepart" + valueParts);
        const idPart = valueParts[valueParts.length - 1].trim();
        console.log(idPart); 
        const id = Number(idPart);
        return isNaN(id) ? null : id; 
      })
      .filter((id) => id !== null) as number[];
  
    console.log('Selected IDs:', selectedIds);
    this.part.categoryIds = selectedIds;
  }

  handleSubmit() {
    console.log(this.part);
    this.partService.updatePart(this.part).subscribe(() => {
      this.router.navigate(['/parts']);
    });
  }
}