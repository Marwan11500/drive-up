import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from './components/button/button.component';
import { InputComponent } from './components/input/input.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import {MatToolbar} from '@angular/material/toolbar';
import {MatButton} from '@angular/material/button';
import {RouterLink} from '@angular/router';
import { LoginDialogComponent } from './components/login-dialog/login-dialog.component';
import { TwoFaComponent } from './components/two-fa/two-fa.component';

@NgModule({
  declarations: [
    ButtonComponent,
    InputComponent,
    NavbarComponent,
    LoginDialogComponent,
    TwoFaComponent
  ],
  imports: [
    CommonModule,
    MatToolbar,
    MatButton,
    RouterLink
  ],
  exports: [
    ButtonComponent,
    InputComponent,
    NavbarComponent
  ]
})
export class SharedModule { }
