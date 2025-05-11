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
  username: string = '';
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  password: string = '';
  role: string = '';
  birthDate: Date | null = null;
  vehicleClass : string = '';
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
      username: this.username,
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password,
      role: this.role,
      birthDate: this.birthDate,
      vehicleClass: this.vehicleClass
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
