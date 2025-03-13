import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from '../../../services/person/person.service';
import { PersonTypeService } from '../../../services/personType/person-type.service';
import { PersonType } from '../../../models/person-type';
import { Person } from '../../../models/person';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-person-update',
  standalone: true,
  templateUrl: './person-update.component.html',
  styleUrls: ['./person-update.component.css'],
  imports: [FormsModule]
})
export class PersonUpdateComponent implements OnInit {
  person: Person = { id: 0, name: '', email: '', phone: '', typeId: 0 };
  personTypes: PersonType[] = [];

  constructor(
    private route: ActivatedRoute,
    private personService: PersonService,
    private personTypeService: PersonTypeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const personState = history.state.person;
    if (personState) {
      this.person = { ...personState };
    }
    this.fetchPersonTypes();
  }

  fetchPersonTypes() {
    this.personTypeService.getPersonTypes().subscribe((data) => {
      this.personTypes = data;
    });
  }

  handleSubmit() {
    this.personService.updatePerson(this.person).subscribe(() => {
      this.router.navigate(['/persons']);
    });
  }
}