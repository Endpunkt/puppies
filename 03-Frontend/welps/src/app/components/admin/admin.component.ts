import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { AnimalCharacter } from '../../common/animal-character.enum';
import { Puppy } from '../../common/puppy';
import { Bread } from '../../common/bread';
import { PuppyService } from '../../services/puppy.service';
import { ResponseService } from '../../services/response.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css',
  animations: [
    trigger('slideToggle', [
      state('void', style({ height: '0px', opacity: 0, overflow: 'hidden' })),
      state('*', style({ height: '*', opacity: 1, minWidth: '200px' })),
      transition('void <=> *', animate('1000ms ease-in-out'))
    ])
  ]
})
export class AdminComponent implements OnInit{
  
  
  puppy: Puppy = new Puppy();
  itemsLength: number | undefined;
  selectedFile: File | null = null;
 
  formsVisible: boolean[] = [];
  animalCharacters: AnimalCharacter[] = Object.values(AnimalCharacter) as AnimalCharacter[];  // Array zur Speicherung der Enum-Werte
  selectedCharacters: AnimalCharacter[] = [];

  constructor(private _service: PuppyService, private _response: ResponseService, private _http: HttpClient){
    this.puppy.race = new Bread();
 
  }

  ngOnInit(): void {
      this.itemsCount();
  }


  //Form-Methods-----------------------------------
  onSubmit() {
   debugger
    
    
    console.log("itemslength: " + this.itemsLength);
    if(this.itemsLength != undefined) {
      this.puppy.id = this.itemsLength + 1;
      console.log("PuppyId: " + this.puppy);
      
    }


    if(this.selectedFile){
      const formData: FormData = new FormData();
      formData.append('image', this.selectedFile);
  
      this._response.uploadImage(formData).subscribe({
          next: (response) => {
              console.log("Bild erfolgreich hochgeladen", response);
          },
          error: (error) => {
              console.error("Fehler beim Hochladen des Bildes:", error);
          }
      });
      
      this.puppy.image = "/assets/images/welps/" + this.selectedFile.name;

      const puppyJson2 = JSON.stringify(this.puppy);
      // Objekt in JSON-Format umwandeln
      const puppyJson = JSON.stringify({
        id: null,
        name: this.puppy.name,
        color: this.puppy.color,
        weight: this.puppy.weight,
        height: this.puppy.height,
        image: this.puppy.image,
        characteristic: this.puppy.characteristic,
        race: { id: null, race: this.puppy.race?.race }, // Mapping ohne Unterstrich
        price: this.puppy.price
      });

      console.log("Puppy JSON: " + puppyJson );
      console.log("Puppy JSON 2: " + puppyJson2 );
      
      this._response.uploadPuppy(puppyJson).subscribe({
        next: (response) => {
            console.log(response); // Ausgabe: "Upload erfolgreich"
        },
        error: (error1) => {
            console.log('Fehler:', error1);
        }
    });

    } else {
      console.error('Keine Datei ausgewählt.');
    }
    console.log(this.puppy);
    console.log(this.itemsLength);
    debugger
  }

  onCheckedBox(aCharacter: AnimalCharacter, event: Event) {
    const isChecked = (event.target as HTMLInputElement).checked;

    if (isChecked) {
        this.selectedCharacters.push(aCharacter); // Verwandle Enum in String
    } else {
        this.selectedCharacters = this.selectedCharacters.filter(c => c !== aCharacter.toString());
    }
    this.puppy.characteristic = this.selectedCharacters;
}

  toggleForm(index: number) {
    // Wenn der Index nicht im Array ist, initialisieren wir ihn als false
    if (this.formsVisible[index] === undefined) {
      this.formsVisible[index] = false;
    }
    this.formsVisible[index] = !this.formsVisible[index];
  }

  onPriceInput(event: Event): void{
    const inputElement = event.target as HTMLInputElement;
    let inputValue = inputElement.value;

    //1. replace komma with dott
    inputValue = inputValue.replace(',', '.');

    //2.remove chars
    inputValue = inputValue.replace('/[^0-9.]/g', '');

    //3. searching after second 
    const parts = inputValue.split('.');
    if(parts.length > 2){
      inputValue = parts[0] + '.' + parts[1];
    }
    if(inputValue != undefined)
      this.puppy.price = parseFloat(inputValue);
  }

  onFileSelected(event: any) {
    if(event.target.files.length > 0){
      const file = event.target.files[0];
      this.selectedFile = file;
    }
  }

  itemsCount() {
    this._service.getPuppyList(0, 100).subscribe(data => { 
      this.itemsLength = data.page.totalElements;
      console.log(this.itemsLength);
      
    });
  }

}


