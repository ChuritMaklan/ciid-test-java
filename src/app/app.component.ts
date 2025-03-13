import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import {NavbarComponent} from './components/navbar/navbar.component';
 // Import RouterModule

@Component({
  selector: 'app-root',
  standalone: true,
  template: `<app-navbar></app-navbar><router-outlet></router-outlet>`,
  imports: [RouterModule, NavbarComponent] // Add RouterModule to imports
})
export class AppComponent {}