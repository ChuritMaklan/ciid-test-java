import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../../models/category';
import {BasicService} from '../basic/basic.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService{
  private subUrl = '/categories';

  constructor(private basicService: BasicService) {}

  getCategories(){
    return this.basicService.getData(this.subUrl);
  }

  addCategory(category: Category): Observable<Category> {
    return this.basicService.addData(this.subUrl, category);
  }

  updateCategory(category: Category): Observable<Category> {
    return this.basicService.updateData(this.subUrl, category.id, category);
  }

  deleteCategory(id: number): Observable<void> {
    return this.basicService.deleteData(this.subUrl, id);
  }
}