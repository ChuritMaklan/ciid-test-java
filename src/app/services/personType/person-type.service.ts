import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PersonType } from '../../models/person-type';

@Injectable({
  providedIn: 'root'
})
export class PersonTypeService {
  private apiUrl = 'http://localhost:8080/person-types';

  constructor(private http: HttpClient) {}

  getPersonTypes(): Observable<PersonType[]> {
    return this.http.get<PersonType[]>(this.apiUrl);
  }

  addPersonType(personType: PersonType): Observable<PersonType> {
    return this.http.post<PersonType>(this.apiUrl, personType);
  }

  updatePersonType(personType: PersonType): Observable<PersonType> {
    return this.http.put<PersonType>(`${this.apiUrl}/${personType.id}`, personType);
  }

  deletePersonType(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}