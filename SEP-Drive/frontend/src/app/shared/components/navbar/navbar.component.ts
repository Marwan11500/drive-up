import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { AuthService } from '../../../auth/auth.service';

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
    private readonly authService: AuthService
    ) {}

  ngOnInit(): void {
    this.authService.currentUser.subscribe((user) => {
      if (user) {
        this.isLoggedIn = true;
        this.username = user.username ? user.username : 'No username';
        this.photoUrl = user.photoUrl ? user.photoUrl : 'assets/placeholder.png';
      } else {
        this.isLoggedIn = false;
        this.username = '';
        this.photoUrl = '';
      }
    });
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
