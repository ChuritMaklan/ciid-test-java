import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Person } from '../../models/person';
import {BasicService} from '../basic/basic.service';

@Injectable({
  providedIn: 'root'
})
export class PersonService{
  private subUrl = '/persons';

  constructor(private basicService: BasicService) {}

  getPersons(){
    return this.basicService.getData(this.subUrl);
  }

  addPerson(person: Person): Observable<Person> {
    return this.basicService.addData(this.subUrl, person);
  }

  updatePerson(person: Person): Observable<Person> {
    return this.basicService.updateData(this.subUrl, person.id, person);
  }

  deletePerson(id: number): Observable<void> {
    return this.basicService.deleteData(this.subUrl, id);
  }
}