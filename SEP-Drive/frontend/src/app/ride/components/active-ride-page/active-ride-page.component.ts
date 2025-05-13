import {Component, OnInit} from '@angular/core';
import {Ride, VehicleClass} from '../../models/ride.model';
import {Location} from '../../models/location.model'
import {Router} from '@angular/router';
import {RideRequestService} from '../../services/ride-request.service';

@Component({
  selector: 'app-active-ride-page',
  standalone: false,
  templateUrl: './active-ride-page.component.html',
  styleUrl: './active-ride-page.component.scss'

})
export class ActiveRidePageComponent implements OnInit {

  activeRide!: Ride;

  constructor(
    private rideService: RideRequestService,
    private router: Router) {
  }


  deactivateRide() {

    const user = JSON.parse(<string>localStorage.getItem('currentUser'));
    const username = user?.username;

    this.rideService.deactivateRide(username).subscribe({
      next: () => {
        this.router.navigate(['/ride/request']);
      },
      error: (err) => {
        console.log(err)
      }
    })
  }

  ngOnInit() {

    const user = JSON.parse(<string>localStorage.getItem('currentUser'));
    const username = user?.username;

    this.rideService.getRide(username).subscribe({
        next: response => {
          this.activeRide = this.mapToRide(response);
          console.log('myride', this.activeRide);
        },
        error: err => console.log('myerror', err)
      }
    )
  }

  private mapToRide(raw: any): Ride {
    const pickup: Location = {
      // name: 'raw.name
      latitude: Number(raw.startLatitude),
      longitude: Number(raw.startLongitude),
      address: raw.startAddress || undefined,
    };

    const dropoff: Location = {
      // name: 'raw.name
      latitude: Number(raw.destinationLatitude),
      longitude: Number(raw.destinationLongitude),
      address: raw.destinationAddress || undefined,
    };

    const ride: Ride = {
      pickup,
      dropoff,
      stopovers: [], // TODO UPDATE WITH STOPOVERS WHEN AVAILABLE
      vehicleClass: raw.vehicleClass as VehicleClass,
      active: true
    };

    return ride;
  }
}
