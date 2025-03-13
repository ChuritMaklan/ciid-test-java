import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonTypeUpdateComponent } from './person-type-update.component';

describe('PersonTypeUpdateComponent', () => {
  let component: PersonTypeUpdateComponent;
  let fixture: ComponentFixture<PersonTypeUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonTypeUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonTypeUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
