import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProfilePageComponent} from './profile/profile-page/profile-page.component';

const routes: Routes = [
  { path: ':username', component: ProfilePageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
