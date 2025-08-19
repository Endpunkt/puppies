import { Component } from '@angular/core';
import { Puppy } from '../../common/puppy';
import { PuppyService } from '../../services/puppy.service';
import { Observable } from 'rxjs/internal/Observable';
import { ActivatedRoute, Route } from '@angular/router';
import { AnimalCharacter } from '../../common/animal-character.enum';
import { ItemMenuListComponent } from '../item-menu-list/item-menu-list.component';



@Component({
  selector: 'app-puppy-details',
  templateUrl: './puppy-details.component.html',
  styleUrl: './item-detail.component.css'
})
export class PuppyDetailsComponent {
  
  puppy: Puppy= new Puppy();
  itemsLength: number | undefined;

  nextId: number | undefined;
  prevId: number | undefined;
  puppyId: number | undefined;
  

  constructor( private _puppyService: PuppyService, private _route: ActivatedRoute){}

  ngOnInit():void{
    this._route.paramMap.subscribe(params => {
      const id = params.get('puppyId'); 
      if (id !== null && id !== undefined) {  
        this.puppyId = +id;
        this.serviceCall(this.puppyId);
        this.itemsCount(); 
      } else {
        console.error('ID parameter is null');
      }
    });
  }
  
  //----------------S E R V I C E __ C A L L ( P U P P Y )---------------------------------
  serviceCall(puppyId: number){
    this._puppyService.getPuppy(puppyId).subscribe(
      data => {
        this.puppy = data;
          if (data.characteristic) {
            this.puppy.characteristic = data.characteristic.map(
              (char: string) => AnimalCharacter[char as keyof typeof AnimalCharacter]
          );
        }
        
      }
    );
  }

  //-----------------G E T P U P P Y_____(service_call)-----------------------------------
  getPuppy() {
    const thePuppyId: number = +this._route.snapshot.params['id'];
    this.serviceCall(thePuppyId);   
  } 
  
  //------------------I T E M S C O U N T_____(calculateNavigationIds)---------------------
  itemsCount() {
    this._puppyService.getPuppyList(0, 100).subscribe(data => { 
      this.itemsLength = data.page.totalElements;
      this.calculateNavigationIds();
    });
  }



  //-----------------C A L C U L A T E N A V I G A T I O N I D S--------------------------
  calculateNavigationIds() {
     
    if (this.itemsLength != undefined && this.puppyId !== undefined ) {

      this.nextId = this.puppyId < this.itemsLength ? this.puppyId + 1 :  1;
      this.prevId = this.puppyId > 1 ? this.puppyId - 1 : this.itemsLength;
      console.log("*calculateNavigationIds()***************");
      console.log(`***itemsLength: ${this.itemsLength}. \npuppyId: ${this.puppyId}***\n`);
      console.log("nextId--------->"+  this.nextId);
      console.log("prevtId--------->"+  this.prevId);
    }else{
      console.log("Varaiblen sind undefined");
    }
  }


}
