import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

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
  usernameControl = new FormControl();
  options: string[] = [];
  filteredOptions!: Observable<string[]>;

  constructor(
    private readonly dialogue: MatDialog,
    private readonly router: Router,
    ) {}

  ngOnInit(): void {
    // Check if the username is stored in localStorage
    const storedUser = localStorage.getItem('loggedInUser');

    if (storedUser) {
      // If there is a user logged in, display the information
      this.isLoggedIn = true;
      this.username = storedUser;
      this.photoUrl = 'assets/placeholder.png';
    } else {
      // If no user is logged in, don't show it
      this.isLoggedIn = false;
    }
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

  searchUser(username: string) {
    console.log('searching for user:', username);
    if (username && username.trim().length > 0) {
      console.log('searching for user:', username);
      this.router.navigate([`/${username}`]).then(() => {
        this.usernameControl.reset();
      });
    }
  }

  goToProfile() {
    console.log('going to profile');
    this.router.navigate([`/${(this.username)}`]);
  }

  goHome() {
    console.log('going home');
    this.router.navigate(['/']);
  }

}
