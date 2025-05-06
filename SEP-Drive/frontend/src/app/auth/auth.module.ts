import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from './auth-routing.module';
import { RegisterComponent } from './register/register.component';
import { TwoFactorComponent } from './two-factor/two-factor.component';


@NgModule({
  declarations: [
    RegisterComponent,
    TwoFactorComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule
  ]
})
export class AuthModule { }
