import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonTypeService } from '../../../services/personType/person-type.service';
import { PersonType } from '../../../models/person-type';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-person-type',
  standalone: true,
  templateUrl: './person-type.component.html',
  styleUrls: ['./person-type.component.css'],
  imports: [CommonModule, FormsModule]
})
export class PersonTypeComponent implements OnInit {
  types: PersonType[] = [];
  typeName: string = '';

  constructor(private personTypeService: PersonTypeService, private router: Router) {}

  ngOnInit(): void {
    this.fetchTypes();
  }

  fetchTypes() {
    this.personTypeService.getPersonTypes().subscribe((data) => {
      this.types = data;
    });
  }

  handleAddType() {
    if (!this.typeName) {
      alert("Type name is required");
      return;
    }
    
    const newPersonType: PersonType = { id: Date.now(), typeName: this.typeName }; // Create a new PersonType object
    this.personTypeService.addPersonType(newPersonType).subscribe(() => {
      this.fetchTypes();
      this.typeName = ''; // Clear the input field
    });
  }

  handleUpdatePersonType(personType: PersonType) {
    this.router.navigate(['/person-types/update', personType.id], { state: { personType } });
  }

  handleDeleteType(id: number) {
    this.personTypeService.deletePersonType(id).subscribe(() => {
      this.fetchTypes();
    });
  }
}