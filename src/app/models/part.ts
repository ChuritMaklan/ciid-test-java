export class Part {
    id: number;
    name: string;
    personId: number;
    price: number;
    quantityInStock: number;
    description: string;
    categoryIds: number[];
  
    constructor(id: number, name: string, personId: number, price: number, quantityInStock: number, description: string, categoryIds: number[]) {
      this.id = id;
      this.name = name;
      this.personId = personId;
      this.price = price;
      this.quantityInStock = quantityInStock;
      this.description = description;
      this.categoryIds = categoryIds;
    }
  }