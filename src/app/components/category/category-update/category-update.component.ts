import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from '../../../services/category/category.service';
import { Category } from '../../../models/category';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-category-update',
  standalone: true,
  templateUrl: './category-update.component.html',
  styleUrls: ['./category-update.component.css'],
  imports: [FormsModule]
})
export class CategoryUpdateComponent implements OnInit {
  category: Category = { id: 0, name: '' };

  constructor(private route: ActivatedRoute, private categoryService: CategoryService, private router: Router) {}

  ngOnInit(): void {
    const categoryState = history.state.category;
    if (categoryState) {
      this.category = { ...categoryState };
    }
  }

  handleSubmit() {
    this.categoryService.updateCategory(this.category).subscribe(() => {
      this.router.navigate(['/categories']);
    });
  }
}