import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PersonType } from '../../models/person-type';
import {BasicService} from '../basic/basic.service';

@Injectable({
  providedIn: 'root'
})
export class PersonTypeService{
  private subUrl = '/person-types';

  constructor(private basicService: BasicService) {}

  getPersonTypes(){
    return this.basicService.getData(this.subUrl);
  }

  addPersonType(personType: PersonType): Observable<PersonType> {
    return this.basicService.addData(this.subUrl, personType);
  }

  updatePersonType(personType: PersonType): Observable<PersonType> {
    return this.basicService.updateData(this.subUrl, personType.id, personType);
  }

  deletePersonType(id: number): Observable<void> {
    return this.basicService.deleteData(this.subUrl, id);
  }
}