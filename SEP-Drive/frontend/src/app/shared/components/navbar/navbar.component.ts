import {Component, OnInit} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent implements OnInit {

  isLoggedIn: boolean = false;
  username: string = '';
  photoUrl: string = '';

  constructor(private readonly dialogue: MatDialog) {}

  ngOnInit(): void {
        this.isLoggedIn = true;
        this.username = 'Ziad Morsy';
        this.photoUrl = 'assets/placeholder.png';
    }

  openLoginDialog() {
    const dialogRef = this.dialogue.open(LoginDialogComponent, {
      width: '400px',
      height: '400px',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
