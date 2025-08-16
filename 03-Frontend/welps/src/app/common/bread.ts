export class Bread{
    constructor(
        private _id?: number,
        private _race?: string){}

    get id(): number | undefined{ return this._id;  }
    set id(id: number | undefined){ this._id = id;  }

    get race(): string | undefined{ return this._race;  }
    set race(race: string | undefined){ this._race = race;  }

    toString(): string{
        return `Bread: \n\tid: ${this._id}\n\trace: ${this._race}`;
    }
}