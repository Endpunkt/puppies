import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FoodDetailsComponent } from './food-details.component';
import { ItemMenuListComponent } from '../item-menu-list/item-menu-list.component';
import {  RouterModule, ActivatedRoute, Routes } from '@angular/router';
/*
const routes: Routes = [
  { path: '/puppy/:id', component: PuppyDetailsComponent },
  { path: '**', component: ItemMenuListComponent}
  
]*/

@NgModule({
    declarations: [FoodDetailsComponent],
    imports: [CommonModule, RouterModule],
    exports: [FoodDetailsComponent]
  })
export class FoodModule { }