import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';
import {Food } from '../common/food'
import {Puppy} from '../common/puppy';
import { map } from 'rxjs';
import { Category } from '../common/category';


@Injectable({
  providedIn: 'root'
})
export class PuppyService {

  private url: string = "http://localhost:8080/api/productObjects"
  private baseUrl: string = "http://localhost:8080/api/puppyFood/dto";

  private categoryUrl: string = "http://localhost:8080/api/category";
  

  constructor(private _httpClient:HttpClient) { }

  getPuppy(puppyId: number): Observable<Puppy>{
    const puppyUrl = `${this.baseUrl}/singlePuppyDTO/${puppyId}`;
    return this._httpClient.get<Puppy>(puppyUrl);
  }

  getFood(foodId: number): Observable<Food>{
    const foodUrl = `${this.baseUrl}/singleFoodDTO/${foodId}`;
    return this._httpClient.get<Food>(foodUrl);
  }

  getPuppyList(page: number, size:number): Observable<GetPuppyResponse>{
    const actualUrl = `${this.baseUrl}/Puppies?page=${page}&size=${size}`;
    return this._httpClient.get<GetPuppyResponse>(actualUrl);
    
  }
  getPuppyListNoSize(): Observable<GetPuppyResponse>{
    const actualUrl = `${this.baseUrl}/cleanProjection`;
    return this._httpClient.get<GetPuppyResponse>(actualUrl);
    
  }

  getFoodList(page: number, size: number): Observable<GetFoodResponse>{
    const actualUrl = `${this.baseUrl}/Food?page=${page}&size=${size}`;
    return this._httpClient.get<GetFoodResponse>(actualUrl);
  }

  getFoodListNoSize(): Observable<GetFoodResponse>{
    const actualUrl = `${this.baseUrl}/Food`;
    return this._httpClient.get<GetFoodResponse>(actualUrl);
  }

  getCategories(): Observable<Category[]>{
    return  this._httpClient.get<GetCategoryResponse>(this.categoryUrl).pipe(
    map(response => response._embedded.category)
  )
}

//Search Puppies
searchPuppies(theKeyword: string, page: number = 0): Observable<GetPuppyResponse>{
  const searchUrl = `${this.baseUrl}/search/PuppyDTO/${theKeyword}?`
  +  `page=${page}&size=8`;

  return this.getPuppies(searchUrl);
}

private getPuppies(searchUrl: string): Observable<GetPuppyResponse> {
  return this._httpClient.get<GetPuppyResponse>(searchUrl);
}
//________________________________

//Search Foods
searchFoods(theKeyword: string, page: number = 0): Observable<GetFoodResponse>{
  console.log("service -----> searchFoods :: page=" + page + " size=9");
  const searchUrl = `${this.baseUrl}/search/FoodDTO/${theKeyword}?`
  + `page=${page}&size=8`;
  console.log(searchUrl);
  return this.getFoods(searchUrl);
}

private getFoods(searchUrl: string): Observable<GetFoodResponse> {
  console.log("service -----> getFoods( )");
  return this._httpClient.get<GetFoodResponse>(searchUrl);
}
//______________________________________


}



interface GetPuppyResponse {
  content: Puppy[];
  page: {
      size: number;
      number: number;
      totalElements: number;
      totalPages: number;
  };
}


interface GetFoodResponse{
  content: Food[];
  page: {
      size: number;
      number: number;
      totalElements: number;
      totalPages: number;
  };
}





interface GetCategoryResponse{
    _embedded:{
      category: Category[];
    }
}

interface getResponseFood{
  foods: Food[];
}


