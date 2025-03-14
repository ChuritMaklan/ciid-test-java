import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Part } from '../../models/part';
import {BasicService} from '../basic/basic.service';

@Injectable({
  providedIn: 'root'
})
export class PartService{
  private subUrl = '/parts';

  constructor(private basicService: BasicService) {}

  getParts(){
    return this.basicService.getData(this.subUrl);
  }

  addPart(part: Part): Observable<Part> {
    return this.basicService.addData(this.subUrl, part);
  }

  updatePart(part: Part): Observable<Part> {
    return this.basicService.updateData(this.subUrl, part.id, part);
  }

  deletePart(id: number): Observable<void> {
    return this.basicService.deleteData(this.subUrl, id);
  }
}