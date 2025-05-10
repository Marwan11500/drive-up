import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-two-fa',
  standalone: false,
  templateUrl: './two-fa.component.html',
  styleUrl: './two-fa.component.scss'
})
export class TwoFaComponent {
  verificationCode: string = '';

  constructor(private dialogRef: MatDialogRef<TwoFaComponent>) {}

  verifyCode() {
    console.log('Eingegebener Code:', this.verificationCode);
    // Hier kannst du die Code-Verifizierung gegen dein Backend machen
    this.dialogRef.close(this.verificationCode); // gibt den Code zurück
  }
}
