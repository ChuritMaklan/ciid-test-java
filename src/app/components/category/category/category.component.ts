import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../../../services/category/category.service';
import { Category } from '../../../models/category';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-category',
  standalone: true,
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
  imports: [FormsModule, CommonModule]
})
export class CategoryComponent implements OnInit {
  categories: Category[] = [];
  name: string = '';

  constructor(private categoryService: CategoryService, private router: Router) {}

  ngOnInit(): void {
    this.fetchCategories();
  }

  fetchCategories() {
    this.categoryService.getCategories().subscribe({
      next: (data) => (this.categories= data),
      error: (err) =>{
        console.error('Error while fetching categories', err);
      },
    });
  }

  handleAddCategory() {
    if (!this.name) {
      alert("Category name is required");
      return;
    }
  
 
    this.categoryService.addCategory({ id: 0, name: this.name }).subscribe(() => {
      this.fetchCategories();
      this.name = ''; 
    });
  }

  handleUpdateCategory(category: Category) {
    this.router.navigate(['/categories/update', category.id], { state: { category } });
  }

  handleDeleteCategory(id: number) {
    this.categoryService.deleteCategory(id).subscribe(() => {
      this.fetchCategories();
    });
  }
}