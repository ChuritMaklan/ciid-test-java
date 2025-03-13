import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderComponent } from './components/order/order/order.component'; 
import { PersonComponent } from './components/person/person/person.component'; 
import { PersonTypeComponent } from './components/personType/person-type/person-type.component'; 
import { PartComponent } from './components/part/part/part.component'; 
import { CategoryComponent } from './components/category/category/category.component'; 
import { OrderUpdateComponent } from './components/order/order-update/order-update.component'; 
import { PersonUpdateComponent } from './components/person/person-update/person-update.component'; 
import { PersonTypeUpdateComponent } from './components/personType/person-type-update/person-type-update.component'; 
import { UpdatePartComponent } from './components/part/part-update/part-update.component'; 
import { CategoryUpdateComponent } from './components/category/category-update/category-update.component'; 


export const routes: Routes = [
  { path: 'orders', component: OrderComponent },
  { path: 'categories', component: CategoryComponent },
  { path: 'persons', component: PersonComponent },
  { path: 'person-types', component: PersonTypeComponent },
  { path: 'parts', component: PartComponent },
  { path: 'orders/update/:id', component: OrderUpdateComponent },
  { path: 'categories/update/:id', component: CategoryUpdateComponent },
  { path: 'persons/update/:id', component: PersonUpdateComponent },
  { path: 'person-types/update/:id', component: PersonTypeUpdateComponent },
  { path: 'parts/update/:id', component: UpdatePartComponent }

  // Define other routes here
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}