import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RideRoutingModule } from './ride/ride-routing.module'

const routes: Routes = [
  {path: 'ride', loadChildren: () => RideRoutingModule}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
