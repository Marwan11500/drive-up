import { Location } from './location.model';

export interface Ride{
  pickup: Location;
  dropoff: Location;
  vehicleClass: 'klein' | 'mittel' | 'deluxe' //TODO: change this to enum to avoid hard-coding
  active: boolean;
}
