import { Component, ViewChild, ElementRef } from '@angular/core';
import emailjs from '@emailjs/browser';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {
  @ViewChild('form') formRef: ElementRef | undefined;
  
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor() { }

  sendEmail(form: NgForm) {
    console.log("Das ist der formRef: " + form.value.message);
    if (form.valid && this.formRef?.nativeElement) {
      emailjs.sendForm('service_ck6tw9q', 'template_39mpms8', this.formRef.nativeElement, 'g1GLoABDWJOFgqrL2')
        .then(() => {
          this.successMessage = 'Deine Nachricht wurde erfolgreich gesendet!';
          this.errorMessage = null;
          form.resetForm();
        }, (error) => {
          this.errorMessage = 'Ein Fehler ist aufgetreten: ' + error.text;
          this.successMessage = null;
        });
    }
    console.log("Das ist der formRef: " + form.value.message);
  }

  sendEmail2(form: NgForm) {
    if (form.valid) {
      // Verwenden der send()-Methode mit den Daten aus dem Formular
      emailjs.send('service_ck6tw9q', 'template_39mpms8', form.value, 'g1GLoABDWJOFgqrL2')
        .then(() => {
          this.successMessage = 'Deine Nachricht wurde erfolgreich gesendet!';
          this.errorMessage = null;
          form.resetForm();
        }, (error) => {
          this.errorMessage = 'Ein Fehler ist aufgetreten: ' + error.text;
          this.successMessage = null;
        });
    } else {
      this.errorMessage = 'Bitte fülle alle erforderlichen Felder aus.';
    }
  }

  testEmail() {
  // Dummy-Daten für den Test
  const dummyData = {
    user_name: 'Test-Benutzer',
    user_email: 'test@example.com',
    message: 'Dies ist eine Testnachricht.',
  };

  emailjs.send('service_ck6tw9q', 'template_39mpms8', dummyData, 'g1GLoABDWJOFgqrL2')
    .then(() => {
      console.log('Test-E-Mail erfolgreich gesendet!');
    }, (error) => {
      console.log('Fehler beim Senden der Test-E-Mail:', error);
      console.log(error); // Zeigt uns die komplette Fehlermeldung
    });
}


}