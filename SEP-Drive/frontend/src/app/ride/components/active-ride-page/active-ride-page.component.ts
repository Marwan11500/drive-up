import {Component} from '@angular/core';
import {Ride, VehicleClass} from '../../models/ride.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-active-ride-page',
  standalone: false,
  templateUrl: './active-ride-page.component.html',
  styleUrl: './active-ride-page.component.scss'

})
export class ActiveRidePageComponent {

  constructor(private router: Router) {
  }

  //TODO FETCH RIDE
  mockupride: Ride = {
    pickup: {name: "PICK ME UP", address: "123456 Essen", latitude: 123456, longitude: 9876542},
    dropoff: {name: "DROP ME OFF", address: "123456 Essen", latitude: 123456, longitude: 9876542},
    stopovers: [{name: "STOP BY", address: "123456 Essen", latitude: 123456, longitude: 9876542},
      {name: "STOP BY", address: "123456 Essen", latitude: 123456, longitude: 9876542},
      {name: "STOP BY", address: "123456 Essen", latitude: 123456, longitude: 9876542}],
    vehicleClass: VehicleClass.SMALL,
    active: true
  }

  //todo add deactivation logic
  deactivateRide() {
    this.router.navigate(['/ride/request']);
    console.log("deactivated");
  }
}
