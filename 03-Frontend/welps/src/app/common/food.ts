import { AnimalCharacter } from "./animal-character.enum";
import { FoodType } from "./foodtype.enum";
import { Puppy } from "./puppy";


export class Food {
    constructor( 
                private _id?: number,
                private _foodId?: number,
                private _name?: string, 
                private _objectName?: string,
                private _foodType?: FoodType,
                private _image?: string,
                private _count?: number,
                private _price?: number
             
                
                      
    ){}

    get id(): number|undefined{    return this._id;     }
    set id(id:number|undefined){   this._id = id;       }

    get foodId(): number| undefined{    return this._foodId;}
    set foodId(foodId:number| undefined){   this._foodId = foodId;}

    get name(): string|undefined{   return this._name;  }
    set name(name: string|undefined){ this._name = name;}

    get objectName(): string|undefined{ return this._objectName;    }
    set objectName(name: string|undefined){ this._objectName = name;}

    get count(): number|undefined{  return this._count;     }
    set count(count: number|undefined){ this._count=count;  }

    get foodType(): FoodType|undefined{ return this._foodType;  }
    set foodType(foodType: FoodType|undefined){ this._foodType = foodType;  }

    get image(): string|undefined{    return this._image; }
    set image(image: string|undefined){ this._image = image;}

    get price(): number|undefined{  return this._price;     }
    set price(price: number|undefined){ this._price = price;}

    
}
