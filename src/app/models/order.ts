// src/app/models/order.ts
import { OrderItem } from './order-item';

export class Order {
  constructor(
    public id: number,
    public personId: number,
    public orderDate: Date,
    public orderItems: OrderItem[] // Use an array instead of a Set
  ) {}

  // Add a part to the order
  handleAddPart(partId: number, quantity: number) {
    const existingItem = this.orderItems.find(
      (item) => item.partId === partId
    );

    if (existingItem) {
      // Update quantity if the part already exists
      existingItem.quantity += quantity;
    } else {
      // Add a new part to the order
      this.orderItems.push(new OrderItem(0, partId, quantity, 0));
    }
  }

  // Remove a part from the order
  handleRemovePart(partId: number) {
    const index = this.orderItems.findIndex((item) => item.partId === partId);

    if (index !== -1) {
      this.orderItems.splice(index, 1); // Remove the item at the found index
    }
  }

  // Update the quantity of a part in the order
  updateQuantity(partId: number, quantity: number) {
    const existingItem = this.orderItems.find(
      (item) => item.partId === partId
    );

    if (existingItem) {
      existingItem.quantity = quantity;
    }
  }
}
