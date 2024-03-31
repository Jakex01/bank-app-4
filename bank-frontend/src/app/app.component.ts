import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MainPageComponent } from "../main-page/main-page.component";
import { FontAwesomeModule } from "@fortawesome/angular-fontawesome";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MainPageComponent, FontAwesomeModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'bank-frontend';
}
