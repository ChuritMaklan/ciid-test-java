import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Part } from '../../models/part';

@Injectable({
  providedIn: 'root'
})
export class PartService {
  private apiUrl = 'http://localhost:8080/parts';

  constructor(private http: HttpClient) {}

  getParts(): Observable<Part[]> {
    return this.http.get<Part[]>(this.apiUrl);
  }

  addPart(part: Part): Observable<Part> {
    return this.http.post<Part>(this.apiUrl, part);
  }

  updatePart(part: Part): Observable<Part> {
    return this.http.put<Part>(`${this.apiUrl}/${part.id}`, part);
  }

  deletePart(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}