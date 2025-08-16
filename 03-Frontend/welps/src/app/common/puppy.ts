import { AnimalCharacter } from "./animal-character.enum";
import { Bread } from "./bread";



export class Puppy {
    constructor(
      private _id?: number,
      private _puppyId?: number,
      private _name?: string,
      private _image?: string,
      private _height?: number,
      private _weight?: number,
      private _color?: string,
      private _characteristic?: AnimalCharacter[],
      private _race?: Bread,
      private _price?: number,
    ) {}
  
    
    get id(): number | undefined {  return this._id;            }   
    set id(value: number | undefined) {   this._id = value;     }  

    get puppyId(): number | undefined { return this._puppyId;   }
    set puppyId(puppyId: number | undefined){   this._puppyId = puppyId;}
  
    get name(): string | undefined{   return this._name;        }
    set name(name: string | undefined){   this._name = name;    }
    
    get weight(): number | undefined {  return this._weight;    }
    set weight(value: number | undefined) {this._weight = value;}
  
    get price(): number | undefined {   return this._price;     }
    set price(value: number | undefined) {this._price = value;  }
  
    get image(): string | undefined {   return this._image;     }
    set image(value: string | undefined) {this._image = value;  }
  
    
    get color(): string | undefined {     return this._color;     }
    set color(color: string){   this._color = color;              }

    get height(): number | undefined {  return this._height;      }
    set height(height:number){          this._height = height;    }

    get race(): Bread | undefined {    return this._race;          }
    set race(race: Bread ){     this._race = race;                  }

    get characteristic(): AnimalCharacter[] | undefined{ return this._characteristic  }
    set characteristic(ch: AnimalCharacter[]){   this._characteristic = ch;           }

    toJSON(): object {
      return {
          id: this._id,
          puppyId: this._puppyId,
          name: this._name,
          image: this._image,
          height: this._height,
          weight: this._weight,
          color: this._color,
          characteristic: this._characteristic,
          race: this._race,
          price: this._price,
      };
  }
  }
