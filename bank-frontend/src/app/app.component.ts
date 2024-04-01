import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MainPageComponent } from "../main-page/main-page.component";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";
import {animate, style, transition, trigger} from "@angular/animations";
import {LoginComponent} from "../login/login.component";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MainPageComponent, FontAwesomeModule, LoginComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        style({ opacity: 0 }), // Start with an opacity of 0
        animate('2s', style({ opacity: 1 })) // Animate to opacity 1 over 2 seconds
      ])
    ])
  ]
})
export class AppComponent {
  title = 'bank-frontend';
}
