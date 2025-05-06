import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProfilePageComponent} from './profile/profile-page/profile-page.component';
import { RideRoutingModule } from './ride/ride-routing.module';

const routes: Routes = [
  { path: ':username', component: ProfilePageComponent },
  { path: 'ride', loadChildren: () => RideRoutingModule }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
