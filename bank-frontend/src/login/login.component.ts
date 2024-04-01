import {Component, HostListener} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    NgClass
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  isActive = false; // Controls which form is active

  constructor() { }

  toggleActive(isActive: boolean): void {
    this.isActive = isActive;
  }
}
