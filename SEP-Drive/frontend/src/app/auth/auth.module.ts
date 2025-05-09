import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from './auth-routing.module';
import { RegisterComponent } from './register/register.component';
import { TwoFactorComponent } from './two-factor/two-factor.component';
import { FormsModule } from '@angular/forms';
import {UsersService} from './services/users.service';


@NgModule({
  declarations: [
    RegisterComponent,
    TwoFactorComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    FormsModule,
  ],
  providers: [
    UsersService
  ]
})
export class AuthModule { }
