import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-login-dialog',
  standalone: false,
  templateUrl: './login-dialog.component.html',
  styleUrl: './login-dialog.component.scss'
})
export class LoginDialogComponent {

  constructor(public dialogRef: MatDialogRef<LoginDialogComponent>) {}

  closeDialog(): void {
    this.dialogRef.close();
  }
}
