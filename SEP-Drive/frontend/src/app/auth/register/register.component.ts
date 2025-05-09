import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import {UsersService} from '../services/users.service';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  Username: string = '';
  Firstname: string = '';
  Lastname: string = '';
  Email: string = '';
  Password: string = '';
  Role: string = '';
  Date: Date | null = null;
  RoleControl = new FormControl('');
  CarControl = new FormControl({ value: '', disabled: true });

  // 🔹 Passwort-Anzeige umschalten
  hidePassword = true;
  constructor(private usersService: UsersService) {
    this.RoleControl.valueChanges.subscribe(role => {
      if (role === 'Driver') {
        this.CarControl.enable(); // aktivieren
      } else {
        this.CarControl.disable(); // deaktivieren
        this.CarControl.setValue(''); // optional: zurücksetzen
      }
    });
  }

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

    this.usersService.createUser(userData).subscribe({
      next: response => {
        console.log('Registrierung erfolgreich:', response);
        // Optional: Weiterleitung oder Erfolgsmeldung
      },
      error: error => {
        console.error('Fehler bei der Registrierung:', error);
      }
    });

    console.log('Registrierung abgeschickt!', userData);
  }
}
