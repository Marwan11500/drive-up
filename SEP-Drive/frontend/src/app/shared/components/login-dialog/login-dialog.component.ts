import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { TwoFaComponent } from '../two-fa/two-fa.component';

@Component({
  selector: 'app-login-dialog',
  standalone: false,
  templateUrl: './login-dialog.component.html',
  styleUrl: './login-dialog.component.scss'
})
export class LoginDialogComponent {
  email = '';
  password = '';
  hidePassword = true;

  constructor(
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
    console.log('Login mit:', this.email, this.password);
    this.open2FADialog();
  }

  open2FADialog() {
    const dialogRef = this.dialog.open(TwoFaComponent);

    dialogRef.afterClosed().subscribe(code => {
      if (code) {
        console.log('2FA Code eingegeben:', code);
        // Hier z. B. API call machen
      }
    });
  }
}

