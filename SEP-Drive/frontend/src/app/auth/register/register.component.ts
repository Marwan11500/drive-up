import { Component } from '@angular/core';


@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'] // <-- kleiner Tipp: es heißt `styleUrls`, nicht `styleUrl`
})
export class RegisterComponent {
  // 🔹 Felder für das Formular:
  Username: string = '';
  Firstname: string = '';
  Lastname: string = '';
  Email: string = '';
  Password: string = '';
  Role: string = '';
  Date: Date | null = null;

  // 🔹 Passwort-Anzeige umschalten
  hidePassword = true;

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }


  // 🔹 Registrierung absenden
  onRegister(): void {
    const userData = {
      Username: this.Username,
      Firstname: this.Firstname,
      Lastname: this.Lastname,
      Email: this.Email,
      Password: this.Password,
      Role: this.Role,
      Date: this.Date
    };

    console.log('Registrierung abgeschickt!', userData);
  }
}
