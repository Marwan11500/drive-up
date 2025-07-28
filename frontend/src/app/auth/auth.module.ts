import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from './auth-routing.module';
import { RegisterComponent } from './register/register.component';
import { TwoFaComponent } from '../shared/components/two-fa/two-fa.component';
import { FormsModule } from '@angular/forms';
import {UsersService} from './services/users.service';


@NgModule({
  declarations: [
    RegisterComponent,
    TwoFaComponent
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
