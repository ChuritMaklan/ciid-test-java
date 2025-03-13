import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  selector: 'app-order',
  standalone: true,
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
  imports: [FormsModule, CommonModule],
})
export class OrderComponent implements OnInit {
  orders: Order[] = [];
  persons: Person[] = [];
  parts: Part[] = [];
  personId: string = '';
  orderDate: string = '';
  selectedParts: OrderItem[] = [];
  personMap: { [key: number]: string } = {};
  partMap: { [key: number]: string } = {};

  constructor(
    private orderService: OrderService,
    private personService: PersonService,
    private partService: PartService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchOrders();
    this.fetchPersons();
    this.fetchParts();
  }

  fetchOrders() {
    this.orderService.getOrders().subscribe((data) => {
      this.orders = data;
    });
  }

  fetchPersons() {
    this.personService.getPersons().subscribe((data) => {
      this.persons = data;
      this.persons.forEach((person) => {
        this.personMap[person.id] = person.name;
      });
    });
  }

  fetchParts() {
    this.partService.getParts().subscribe((data) => {
      this.parts = data;
      this.parts.forEach((part) => {
        this.partMap[part.id] = part.name;
      });
    });
  }

  handleAddOrder() {
    if (!this.personId || !this.orderDate || this.selectedParts.length === 0) {
      alert('Person, Order Date, and at least one part are required');
      return;
    }
    const order: Order = new Order(
      0,
      Number(this.personId),
      new Date(this.orderDate),
      this.selectedParts
    );
    console.log(order);
    this.orderService.addOrder(order).subscribe(() => {
      this.fetchOrders();
      this.resetForm();
    });
  }

  handleUpdateOrder(order: Order) {
    this.router.navigate(['/orders/update', order.id], { state: { order } });
  }

  handleDeleteOrder(id: number) {
    this.orderService.deleteOrder(id).subscribe(() => {
      this.fetchOrders();
    });
  }

  handleAddPart(partId: number) {
    console.log('Adding part with ID:', partId);
    const part = this.parts.find((p) => p.id === Number(partId));
    console.log(this.parts);
    if (part) {
      const existingPart = this.selectedParts.find(
        (item) => item.partId === partId
      );
      if (!existingPart) {
        this.selectedParts.push({ id: 0, partId: part.id, quantity: 1, price: 0 });
        console.log('Selected Parts:', this.selectedParts); // Log updated array
      } else {
        alert('This part is already added to the order.');
      }
    }
  }

  handleQuantityChange(partId: number, quantity: number) {
    const partIndex = this.selectedParts.findIndex(
      (item) => item.partId === partId
    );
    if (partIndex > -1) {
      this.selectedParts[partIndex].quantity = Number(quantity);
    }
  }

  removePart(index: number) {
    this.selectedParts.splice(index, 1);
  }

  resetForm() {
    this.personId = '';
    this.orderDate = '';
    this.selectedParts = [];
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
    return `${day} ${month} ${year}`;
  }

  parseInputValue(value: string): number {
    return value ? parseFloat(value) : 1;
  }
}
