import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { TwoFaComponent } from '../two-fa/two-fa.component';
import { AuthService } from '../../../auth/auth.service';

@Component({
  selector: 'app-login-dialog',
  standalone: false,
  templateUrl: './login-dialog.component.html',
  styleUrl: './login-dialog.component.scss'
})
export class LoginDialogComponent {
  username = '';
  password = '';
  hidePassword = true;
  errorMessage: any;

  constructor(
    private authService: AuthService,
    public dialogRef: MatDialogRef<LoginDialogComponent>,
    private dialog: MatDialog
  ) {}

  togglePasswordVisibility(): void {
    this.hidePassword = !this.hidePassword;
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  onLogin() {
    this.authService.loginUser(this.username, this.password).subscribe({
      next: (response) => {
        console.log(response);
        // This won't get called because 401 goes to error
      },
      error: (err) => {
        console.error("Backend Error Response:", err);

        // Check if the error message is for 2FA
        if (err.status === 401 && err.error.includes("Email verification required")) {
          console.log("🔒 2FA Required. Opening Dialog...");
          this.dialogRef.close(); // Close the login dialog
          this.open2FADialog();   // Open the 2FA dialog
        } else {
          this.errorMessage = "Login failed. Please try again.";
        }
      }
    });
  }



  open2FADialog() {
    console.log("Opening 2FA dialog...");
    const dialogRef = this.dialog.open(TwoFaComponent);

    dialogRef.afterClosed().subscribe(code => {
      if (code) {
        console.log('2FA Code eingegeben:', code);

      }
    });
  }
}

