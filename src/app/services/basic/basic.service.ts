import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BasicService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getData(subUrL: String): Observable<any> {
    return this.http.get(this.apiUrl + subUrL);
  }

  getDataById(subURL: String, id: number): Observable<any> {
    return this.http.get(`${this.apiUrl + subURL}/${id}`);
  }

  addData(subUrl: String, item: any): Observable<any> {
    return this.http.post(this.apiUrl + subUrl, item);
  }

  updateData(subUrL: String, id: number, item: any): Observable<any> {
    return this.http.put(`${this.apiUrl + subUrL}/${id}`, item);
  }

  deleteData(subUrL: String, id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl + subUrL}/${id}`);
  }
}
