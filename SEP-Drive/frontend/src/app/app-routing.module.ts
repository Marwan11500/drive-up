import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProfilePageComponent} from './profile/profile-page/profile-page.component';
import { RideRoutingModule } from './ride/ride-routing.module';
import {RegisterComponent} from './auth/register/register.component';
import { TwoFactorComponent } from './auth/two-factor/two-factor.component';
const routes: Routes = [
  { path: 'ride', loadChildren: () => RideRoutingModule },
  { path: 'register', component: RegisterComponent },
  { path: ':username', component: ProfilePageComponent },
  { path: 'two-factor', component: TwoFactorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
