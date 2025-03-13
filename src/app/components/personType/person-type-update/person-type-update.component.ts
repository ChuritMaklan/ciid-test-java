import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonTypeService } from '../../../services/personType/person-type.service';
import { PersonType } from '../../../models/person-type';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-person-type-update',
  standalone: true,
  templateUrl: './person-type-update.component.html',
  styleUrls: ['./person-type-update.component.css'],
  imports: [FormsModule]
})
export class PersonTypeUpdateComponent implements OnInit {
  personType: PersonType = { id: 0, typeName: '' };

  constructor(private route: ActivatedRoute, private personTypeService: PersonTypeService, private router: Router) {}

  ngOnInit(): void {
    const personTypeState = history.state.personType;
    if (personTypeState) {
      this.personType = { ...personTypeState };
    }
  }

  handleSubmit() {
    this.personTypeService.updatePersonType(this.personType).subscribe(() => {
      this.router.navigate(['/person-types']);
    });
  }
}