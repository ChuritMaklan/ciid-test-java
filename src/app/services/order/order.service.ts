import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../../models/order';
import {BasicService} from '../basic/basic.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService{
  private subUrl = '/orders';

  constructor(private basicService: BasicService) {}

  getOrders(){
    return this.basicService.getData(this.subUrl);
  }

  addOrder(order: Order): Observable<Order> {
    return this.basicService.addData(this.subUrl, order);
  }

  updateOrder(order: Order): Observable<Order> {
    return this.basicService.updateData(this.subUrl, order.id, order);
  }

  deleteOrder(id: number): Observable<void> {
    return this.basicService.deleteData(this.subUrl, id);
  }
}