import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RideRoutingModule } from './ride-routing.module';
import { CreateRideComponent } from './create-ride/create-ride.component';
import { ActiveRideComponent } from './active-ride/active-ride.component';


@NgModule({
  declarations: [
    CreateRideComponent,
    ActiveRideComponent
  ],
  imports: [
    CommonModule,
    RideRoutingModule
  ]
})
export class RideModule { }
