import {Component, OnInit} from '@angular/core';

import {Location} from '../../models/location.model'
import {Ride, VehicleClass} from '../../models/ride.model';

import {GeolocationService} from '../../services/geolocation.service';
import {PlacesService} from '../../services/places.service';

import {FormArray, FormControl} from '@angular/forms';
import {catchError, debounceTime, distinctUntilChanged, Observable, of, switchMap} from 'rxjs';
import {map} from 'rxjs/operators';

enum updateType {
    pickup,
    dropoff
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
        vehicleClass: VehicleClass.klein,
        active: false
    };

    pickupControl = new FormControl<Location | string>('');
    dropoffControl = new FormControl<Location | string>('');
    stopoversControl = new FormArray<FormControl<Location | string | null>>([]);

    filteredPickupOptions!: Observable<Location[]>;
    filteredDropoffOptions!: Observable<Location[]>;

    protected readonly updateType = updateType;

    constructor(
        private geolocationService: GeolocationService,
        private placesService: PlacesService
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
        const control = new FormControl<Location | string>('');
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

    //TODO: Validation logic and Submtit logic
    submit() {
        console.log('done');
        this.ride.active = true;
    }
}
