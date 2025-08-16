import { Component, OnInit } from '@angular/core';
import { Puppy } from '../../common/puppy';
import { PuppyService } from '../../services/puppy.service';
import { ActivatedRoute } from '@angular/router';
import { Food } from '../../common/food';




@Component({
  selector: 'app-item-menu-list',
  templateUrl: './item-menu-list.component.html',
  styleUrl: './item-menu-list.component.css'
})
export class ItemMenuListComponent implements OnInit{
  page = 0;
  length = 100;
  pageSize = 8;
  pageElements = 0;
  pageSizeOptions = [8, 12, 16, 20];

 
  categoryId: number = 1;
  previousCategory = 1;
  currentCategory = 1;
  puppies: Puppy[] = [];
  foods: Food[] = [];
  searchMode: boolean = false;



      constructor(private _puppyService: PuppyService,  private _route: ActivatedRoute){}

      ngOnInit(): void {
       
          this._route.paramMap.subscribe(() =>{  this.firstOperation()})
      }

    
      pageMove(event: any){
        //console.log("Here  -----P A G I N A T I O N---- " + event.page);
        this.page = event.pageIndex ;
        this.length = event.length;
        this.pageSize = event.pageSize;
        this.pageElements = event.pageElements;

        if(this.categoryId === 1){
          this.showProducts();
        }else if(this.categoryId === 2){
          this.showFoods();
        }
      }

      showFoods() {
        this.puppies = [];
        console.log("This.page: " + this.page);
        this._puppyService.getFoodList(this.page, this.pageSize).subscribe(
          data => {
            this.foods = data.content;
            this.page = data.page.number;
            this.pageSize = data.page.size;
            this.length = data.page.totalElements;
            this.pageElements = data.page.totalElements;
          },
          error => {
            console.error('Fehler beim Laden der Produkte:', error);
          }
        );
    
      }
  


      showProducts() {
        this.foods = [];
        this._puppyService.getPuppyList(this.page, this.pageSize).subscribe(
          data => {
            this.puppies = data.content;
            this.page = data.page.number;
            this.pageSize = 9;
            this.length = data.page.totalElements;
            this.pageElements = data.page.totalElements;
            
          }
        );
      }


      firstOperation(){
        this.pageSizeOptions = [8, 12, 16, 20];
        const hasCategoryId: boolean = this._route.snapshot.paramMap.has('id');
        if(hasCategoryId){
          this.categoryId = + this._route.snapshot.paramMap.get('id')!;
        }else{
          this.categoryId = 1;
        }
        if(this.previousCategory != this.currentCategory){
          this.categoryId = 1;
        }
   
        this._route.queryParamMap.subscribe(params => {
          const searchObject = params.get('category');
          const theKeyword = params.get('q') || '';
          if(theKeyword.trim() == ''){
            this.pageSizeOptions = [8, 12, 16, 20];
            this.getProductList();
          }else{
            if(searchObject == 'Welpen'){
            
              this.searchPuppies(theKeyword);
            }else if(searchObject == 'Nahrung'){
              console.log("Hier geht es um   ____F O O D____");
              console.log('Current pageSizeOptions:', this.pageSizeOptions);
              this.searchFoods(theKeyword);
            }else{
              this.pageSizeOptions = [8, 12, 16, 20];
              this.getProductList();
            }
          }
        });
        
      }

      getProductList() {
      
        
        const hasCategoryId: boolean = this._route.snapshot.paramMap.has('id');

        if (hasCategoryId) {
          this.categoryId = +this._route.snapshot.paramMap.get('id')!;
        }else{
          this.categoryId = 1;
        }
    
        if (this.categoryId === 1) {
          this.showProducts();
        } else if (this.categoryId === 2) {
          this.showFoods();
        }
      }

      searchPuppies(theKeyWord:string){
        this.foods = [];
        this._puppyService.searchPuppies(theKeyWord, this.page).subscribe(
          data =>{ 
            this.puppies = data.content;
            this.page = data.page.number;
            this.pageSize = 8;
            this.length = data.page.totalPages;
            this.pageElements = data.page.totalElements;
          }
        )
      }

      searchFoods(theKeyWord:string){
        this.puppies = [];
        this._puppyService.searchFoods(
          theKeyWord,this.page).subscribe(
          data =>{ 
            this.foods = data.content;
            this.page = data.page.number;
            this.pageSize = 8;
            this.length = data.page.totalElements;
            this.pageElements = data.page.totalElements;
          
          }
        );
      }

 

  

}


