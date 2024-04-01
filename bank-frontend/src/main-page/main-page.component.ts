import {Component, HostListener} from '@angular/core';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})
export class MainPageComponent {
  isInView: boolean = false;

  constructor() {
    this.checkScroll(); // Initial check in case the component is already in view
  }

  @HostListener('window:scroll')
  checkScroll() {
    const aboutUsSection = document.querySelector('.about-us') as HTMLElement;

    if (aboutUsSection) {
      const componentPosition = aboutUsSection.getBoundingClientRect().top;
      const scrollPosition = window.innerHeight / 1.3; // Adjust this value as needed

      this.isInView = componentPosition < scrollPosition;
    }
  }

  scrollToSection(sectionId: string) {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
  }
}
