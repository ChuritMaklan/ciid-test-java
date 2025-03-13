export class Person {
    id: number;
    name: string;
    email: string;
    phone: string;
    typeId: number;
  
    constructor(id: number, name: string, email: string, phone: string, typeId: number) {
      this.id = id;
      this.name = name;
      this.email = email;
      this.phone = phone;
      this.typeId = typeId;
    }
  }