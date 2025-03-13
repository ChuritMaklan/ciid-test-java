import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '../../../services/order/order.service';
import { PersonService } from '../../../services/person/person.service';
import { PartService } from '../../../services/part/part.service';
import { Order } from '../../../models/order';
import { Person } from '../../../models/person';
import { Part } from '../../../models/part';
import { OrderItem } from '../../../models/order-item';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-order-update',
  standalone: true,
  templateUrl: './order-update.component.html',
  styleUrls: ['./order-update.component.css'],
  imports: [FormsModule, CommonModule],
})
export class OrderUpdateComponent implements OnInit {
  order: Order = new Order(0, 0, new Date(), []);
  persons: Person[] = [];
  parts: Part[] = [];

  constructor(
    private route: ActivatedRoute,
    private orderService: OrderService,
    private personService: PersonService,
    private partService: PartService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const orderState = history.state.order;
    if (orderState) {
      this.order = { ...orderState };
    }
    this.fetchPersons();
    this.fetchParts();
  }

  fetchPersons() {
    this.personService.getPersons().subscribe((data) => {
      this.persons = data;
    });
  }

  fetchParts() {
    this.partService.getParts().subscribe((data) => {
      this.parts = data;
    });
  }

  handleQuantityChange(partId: number, quantity: number) {
    this.order.updateQuantity(partId, quantity);
  }

  handleRemovePart(partId: number) {
    this.order.handleRemovePart(partId);
  }

  handleAddPart(event: Event) {
    const target = event.target as HTMLSelectElement;
    const partId = target.value;
    if (partId) {
      this.order.handleAddPart(parseInt(partId, 10), 1); // Add part with quantity 1
    }
  }

  getPartName(partId: number): string {
    if (!this.parts || !partId) return 'Unknown Part';
    const part = this.parts.find((p) => p.id === partId);
    return part ? part.name : 'Unknown Part';
  }

  handleSubmit() {
    this.orderService.updateOrder(this.order).subscribe(() => {
      this.router.navigate(['/orders']);
    });
  }
}
