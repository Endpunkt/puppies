import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  showNavbar: boolean = false;
  title = 'welps';

  // Der Konstruktor ist jetzt sauber und wird nur für die Abhängigkeitsinjektion verwendet.
  constructor(private _router: Router) { } 

ngOnInit() {
  this._router.events
    .pipe(
      filter((event): event is NavigationEnd => event instanceof NavigationEnd)
    )
    .subscribe((event: NavigationEnd) => {
      const url = event.urlAfterRedirects.split('?')[0];

      // Liste der Pfade, auf denen die Navbar angezeigt werden soll
      const navPaths = ['/galerie', '/food', '/puppy', '/puppies', '/foods', '/category', '/search'];

      // Überprüfe, ob die URL mit einem der Pfade im Array beginnt
      if (navPaths.some(path => url.startsWith(path))) {
        this.showNavbar = true;
      } else {
        this.showNavbar = false;
      }
    });
  }
}
