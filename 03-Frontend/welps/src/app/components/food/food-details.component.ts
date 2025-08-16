import { Component } from '@angular/core';
import { Food } from '../../common/food';
import { ActivatedRoute } from '@angular/router';
import { PuppyService } from '../../services/puppy.service';

@Component({
  selector: 'app-food',
  templateUrl: './food-details.component.html',
  styleUrl: './food-details.component.css'
})
export class FoodDetailsComponent {
  food: Food = new Food();
  

  foodsLength: number | undefined;

  prevId: number | undefined;
  foodId: number | undefined;
  nextId: number | undefined;


  constructor(private _route: ActivatedRoute, private _puppyService: PuppyService){}

  ngOnInit(){
    this._route.paramMap.subscribe(
      params =>{
        const id = +params.get('foodId')!; 
        if(id != undefined && id != null){
          this.foodId = id;
          this.serviceCall(id);
          
          this.foodsCount();
        }
      }
    )

  }

  getFood(){
    const theFoodId: number = +this._route.snapshot.params['foodId'];
    this.serviceCall(theFoodId);
  }

  serviceCall(id: number){
    this._puppyService.getFood(id).subscribe(
      data => 
        this.food = data
    );
  }

  foodsCount(){
    this._puppyService.getFoodList(0, 100).subscribe(data =>{
      this.foodsLength = data.page.totalElements;
      this.calculateNavigationIds();
    }
      
    )
  }

  calculateNavigationIds(){
    if(this.foodId != undefined && this.foodsLength != undefined){

      this.prevId = this.foodId > 1 ? this.foodId  - 1 : this.foodsLength;
      this.nextId = this.foodId < this.foodsLength ? this.foodId + 1 : 1;
    }
  
  }
}
