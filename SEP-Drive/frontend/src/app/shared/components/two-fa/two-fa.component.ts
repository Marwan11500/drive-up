import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import {AuthService} from '../../../auth/auth.service';

@Component({
  selector: 'app-two-fa',
  standalone: false,
  templateUrl: './two-fa.component.html',
  styleUrl: './two-fa.component.scss'
})
export class TwoFaComponent {
  verificationCode: string = '';
  errorMessage: string = '';

  constructor(
    private dialogRef: MatDialogRef<TwoFaComponent>,
    private authService: AuthService // Hier den AuthService importieren
  ) {}

  verifyCode() {
    console.log('Entered Code:', this.verificationCode);

    // 🔍 Retrieve the username (you can get it from localStorage or pass it via Dialog Data)
    const username = localStorage.getItem('loggedInUser');

    if (!username) {
      this.errorMessage = "User not found. Please log in again.";
      return;
    }

    // 🔐 Call the service to verify the code
    this.authService.verifyCode(username, this.verificationCode).subscribe({
      next: (response) => {
        console.log('✅ Verification successful:', response);
        alert('Login Successful!');
        localStorage.setItem('token', response); // Store JWT for future requests
        this.dialogRef.close(); // Close the dialog
        window.location.href="/";
      },
      error: (err) => {
        console.error('❌ Verification failed:', err);
        this.errorMessage = 'Invalid code. Please try again.';
      }
    });
  }
}
