import {Component, OnInit} from '@angular/core';

import {Location} from '../../models/location.model'
import {Ride, VehicleClass} from '../../models/ride.model';

import {GeolocationService} from '../../services/geolocation.service';
import {PlacesService} from '../../services/places.service';

import {FormArray, FormControl, Validators} from '@angular/forms';
import {catchError, debounceTime, distinctUntilChanged, Observable, of, switchMap} from 'rxjs';
import {map} from 'rxjs/operators';
import {Router} from '@angular/router';
import {RideRequestService} from '../../services/ride-request.service';

enum updateType {
  pickup = -2,
  dropoff = -1,
}

@Component({
  selector: 'ride-form',
  standalone: false,
  templateUrl: './ride-form.component.html',
  styleUrl: './ride-form.component.scss',
})
export class RideFormComponent implements OnInit {
  vehicles = Object.values(VehicleClass);
  ride: Ride = {
    pickup: {latitude: 0, longitude: 0},
    dropoff: {latitude: 0, longitude: 0},
    stopovers: [],
    vehicleClass: VehicleClass.SMALL,
    active: false
  };

  pickupControl = new FormControl<Location | string>('', [Validators.required]);
  dropoffControl = new FormControl<Location | string>('', [Validators.required]);
  stopoversControl = new FormArray<FormControl<Location | string | null>>([]);

  filteredPickupOptions!: Observable<Location[]>;
  filteredDropoffOptions!: Observable<Location[]>;

  protected readonly updateType = updateType;

  constructor(
    private geolocationService: GeolocationService,
    private placesService: PlacesService,
    private rideService: RideRequestService,
    private router: Router,
  ) {
  }

  ngOnInit() {
    this.filteredPickupOptions = this.setupAutocomplete(this.pickupControl);
    this.filteredDropoffOptions = this.setupAutocomplete(this.dropoffControl);

    this.ride.stopovers.forEach((loc) => {
      const control = new FormControl<Location | string>(loc);
      this.stopoversControl.push(control);
    });
  }

  onSearch(query: string) {
    if (!query.trim()) return of([]);
    return this.placesService.searchPlaces(query);
  }

  private setupAutocomplete(control: FormControl) {
    return control.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      map(value => typeof value === 'string' ? value : value?.name || ''),
      switchMap(query => this.onSearch(query).pipe(
        catchError(() => of([])),
      ))
    );
  }

  onLocationSelected(location: Location, type: updateType) {
    switch (type) {
      case updateType.pickup:
        this.ride.pickup = location;
        break;
      case updateType.dropoff:
        this.ride.dropoff = location;
        break;
      default:
        throw new Error(`Unknown updateType: ${type}`);
    }
    console.log(this.ride);
  }

  onStopoverLocationSelected(location: Location, index: number) {
    this.ride.stopovers[index] = location;
  }

  addStopover() {
    const control = new FormControl<Location | string>('', [Validators.required]);
    this.stopoversControl.push(control);
    this.ride.stopovers.push({latitude: 0, longitude: 0});
  }

  removeStopover(index: number) {
    this.stopoversControl.removeAt(index);
    this.ride.stopovers.splice(index, 1);
  }

  myLocation() {
    this.geolocationService.getLocation().subscribe({
      next: (myLocation: Location) => {
        this.ride.pickup = myLocation;
        this.ride.pickup.name = "My Location";
        this.pickupControl.setValue(myLocation);
      }
    })
  }

  //TODO: Update with map visualization API
  pinLocation(type: updateType | number) {
    const mockupLocation: Location = {
      latitude: 50,
      longitude: 50,
      name: 'Mockup Location',
      address: '12345 Street City State'
    };

    switch (type) {
      case updateType.pickup:
        this.pickupControl.setValue(mockupLocation);
        this.onLocationSelected(mockupLocation, updateType.pickup);

        break;

      case updateType.dropoff:
        this.dropoffControl.setValue(mockupLocation);
        this.onLocationSelected(mockupLocation, updateType.dropoff);
        break;

      default:
        this.ride.stopovers[type] = mockupLocation;
        const control = this.stopoversControl.at(type);
        control.setValue(mockupLocation);

    }
  }

  get isFormInvalid(): boolean {
    return (
      this.pickupControl.invalid ||
      this.dropoffControl.invalid ||
      this.stopoversControl.controls.some(c => c.invalid)
    );
  }

  submit() {

    const user = JSON.parse(<string>localStorage.getItem('currentUser'));
    const username = user?.username;

    const rideDataJson: any = {
      userName: username,
      vehicleClass: this.ride.vehicleClass,
      startLatitude: `${this.ride.pickup.latitude}`,
      startLongitude: `${this.ride.pickup.longitude}`,
      destinationLatitude: `${this.ride.dropoff.latitude}`,
      destinationLongitude: `${this.ride.dropoff.longitude}`,
      startLocationName: `${this.ride.pickup.name}`,
      destinationLocationName: `${this.ride.dropoff.name}`,
      startAddress: `${this.ride.pickup.address}`,
      destinationAddress: `${this.ride.dropoff.address}`
    };

    console.log("this is the file to send to database:", rideDataJson)

    this.rideService.submitRide(rideDataJson).subscribe({
      next: response => {
        console.log('Submitted:', response);
        this.rideService.setActiveRide(true);
        this.router.navigate(['/ride/active']);
      },
      error: error => {
        console.error('Error:', error);
      }
    });
  }
}
