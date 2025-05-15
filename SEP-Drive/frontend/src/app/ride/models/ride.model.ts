import {Location} from './location.model';

export interface Ride {
  pickup: Location;
  dropoff: Location;
  vehicleClass: VehicleClass;
  active: boolean;
}

export enum VehicleClass {
  SMALL = 'Small',
  MEDIUM = 'Medium',
  LARGE = 'Large'
}
