import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{

  searchCategory: string = 'puppy';  // Standardmäßig ausgewählte Kategorie
  searchQuery: string = '';

  constructor(private _router: Router){}

  ngOnInit(): void {
    
  }

  doSearch(){
    console.log("Taste funktioniert ! ---- ! ---- ! ---- !")
    if(this.searchCategory.trim()){
      this._router.navigate([`/search`], { queryParams: { q: this.searchQuery, category: this.searchCategory } });
    }
  }

}
