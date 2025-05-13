import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import {UsersService} from '../services/users.service';
import { VehicleClass } from '../Constants/VehicleClassEnum';
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
  role: string = '';
  password: string = '';
  birthDate: Date | null = null;
  profilePicture : File | null = null;
  vehicleClass: VehicleClass | null = null;
  RoleControl = new FormControl('');
  CarControl = new FormControl({ value: '', disabled: true });
  // 🔹 Passwort-Anzeige umschalten
  hidePassword = true;
  constructor(private usersService: UsersService) {
    this.RoleControl.valueChanges.subscribe(role => {
      if (role === 'Driver') {
        this.CarControl.enable(); // aktivieren
      } else {
        this.CarControl.disable();// deaktivieren
        this.vehicleClass = null;
      }
    });
  }
  private formatDate(date: Date): string {
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are 0-indexed
    const day = String(date.getDate()).padStart(2, '0');
    const year = date.getFullYear();
    return `${month}/${day}/${year}`;
  }
  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }
  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.profilePicture = file;
    }
  }


  // 🔹 Registrierung absenden
  onRegister(): void {
    const formData = new FormData();
    formData.append('username', this.username);
    formData.append('firstName', this.firstName);
    formData.append('lastName', this.lastName);
    formData.append('email', this.email);
    formData.append('password', this.password);
    formData.append('role', this.role);
    if (this.birthDate) {
      const formattedDate = this.formatDate(this.birthDate);
      formData.append('birthDate', formattedDate);
    }
    if (this.vehicleClass) {
      formData.append('vehicleClass', this.vehicleClass);
    }
    if (this.profilePicture) {
      formData.append('profilePicture', this.profilePicture);
    }

    this.usersService.createUser(formData).subscribe({
      next: (response) => {
        console.log('✅ Registrierung erfolgreich:', response);

        if (response) {
          // ✅ Store in Local Storage
          localStorage.setItem('currentUser', JSON.stringify(response));

          // ✅ Navigate to the main page
          window.location.href = '/';
        } else {
          console.warn("⚠️ Registration response is null or undefined!");
        }
      },
      error: (error) => {
        console.error('❌ Fehler bei der Registrierung:', error.message);
      }
    });
  }

}
