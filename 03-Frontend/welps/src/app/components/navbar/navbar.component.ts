import { Component, Input, input, OnInit, signal, ViewChild } from '@angular/core';
import { Category } from '../../common/category';
import {PuppyService } from '../../services/puppy.service';
import { ItemMenuListComponent } from '../item-menu-list/item-menu-list.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{
  categories: Category[] = [];
  
 
  

  constructor(private _puppyService: PuppyService){

  }

  ngOnInit(): void {
    this.listCategory();
  }

  listCategory(){
    this._puppyService.getCategories().subscribe(
      data => {
        
        this.categories = data;
        console.log("Categories: " + JSON.stringify(data));
      }
    )
  }


 
}
