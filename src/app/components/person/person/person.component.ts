import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonService } from '../../../services/person/person.service';
import { Person } from '../../../models/person';
import { PersonTypeService } from '../../../services/personType/person-type.service';
import { PersonType } from '../../../models/person-type';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-person',
  standalone: true,
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css'],
  imports: [CommonModule, FormsModule]
})
export class PersonComponent implements OnInit {
  persons: Person[] = [];
  personTypes: PersonType[] = [];
  name: string = '';
  email: string = '';
  phone: string = '';
  typeId: string = '';

  constructor(
    private personService: PersonService,
    private personTypeService: PersonTypeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchPersons();
    this.fetchPersonTypes();
  }

  fetchPersons() {
    this.personService.getPersons().subscribe({
      next: (data) => (this.persons= data),
      error: (err) =>{
        console.error('Error while fetching persons', err);
      },
    });
  }

  fetchPersonTypes() {
    this.personTypeService.getPersonTypes().subscribe({
      next: (data) => (this.personTypes= data),
      error: (err) =>{
        console.error('Error while fetching personTypes', err);
      },
    });
  }
  getTypeName(typeId: number): string {
    const type = this.personTypes.find(type => type.id === typeId);
    return type ? type.typeName : 'N/A'; // Return 'N/A' if type is not found
  }

  handleAddPerson() {
    if (!this.name || !this.email || !this.phone || !this.typeId) {
      alert("All fields are required");
      return;
    }
    this.personService.addPerson({ id: 0, name: this.name, email: this.email, phone: this.phone, typeId: +this.typeId })
      .subscribe(() => {
        this.fetchPersons();
        this.name = '';
        this.email = '';
        this.phone = '';
        this.typeId = '';
      });
  }

  handleUpdatePerson(person: Person) {
    this.router.navigate(['/persons/update', person.id], { state: { person } });
  }

  handleDeletePerson(id: number) {
    this.personService.deletePerson(id).subscribe(() => {
      this.fetchPersons();
    });
  }
}