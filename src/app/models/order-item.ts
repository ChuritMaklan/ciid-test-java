export class OrderItem {
    id: number;
    partId: number;
    quantity: number;
    price: number;
  
    constructor(id: number, partId: number, quantity: number, price: number) {
      this.id = id;
      this.partId = partId;
      this.quantity = quantity;
      this.price = price;
    }
  }