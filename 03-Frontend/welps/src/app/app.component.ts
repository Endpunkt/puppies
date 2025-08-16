import { Component, OnInit, ViewChild } from '@angular/core';
import { ItemMenuListComponent } from './components/item-menu-list/item-menu-list.component';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  showNavbar: boolean = false; 
  title = 'welps';
  
 
  
  constructor(private _router: Router){
    this._router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        // Prüfe, ob die Route "/admin" ist
        this.showNavbar = event.url !== '/admin';
      }
    });
  }
}
