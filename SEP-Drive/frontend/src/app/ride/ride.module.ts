import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RideRoutingModule } from './ride-routing.module';
import { TesterComponent } from './components/tester/tester.component'; //TODO REMOVE
import { GeolocationService } from './services/geolocation.service';

@NgModule({
  declarations: [
    TesterComponent //TODO REMOVE
  ],
  imports: [
    CommonModule,
    RideRoutingModule
  ],
  providers: [
    GeolocationService,
  ]
})
export class RideModule { }
