import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RideRoutingModule } from './ride-routing.module';

import { GeolocationService } from './services/geolocation.service';
import { PlacesService } from './services/places.service';


@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    RideRoutingModule,
  ],
  providers: [
    GeolocationService,
    PlacesService
  ]
})
export class RideModule { }
