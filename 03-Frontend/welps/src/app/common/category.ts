export class Category {

    constructor(private _id: number, private _categoryName:string){

    }

    get id():number | undefined{ return this._id; }
    get categoryName(): string | undefined {  return this._categoryName;  }

    set id(id:number){  this._id = id; }
    set categoryName(category: string){ this._categoryName = category;}

    toString():string{  return `Id: ${this._id};\n Category-Name: ${this._categoryName}`;}

}
