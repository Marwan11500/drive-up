import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Ride} from '../models/ride.model';

@Injectable({
  providedIn: 'root'
})
export class RideRequestService {

  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080'; //TODO FIND URL
  }

  public submitRide(ride: Ride) {
    return this.http.post<Ride>(this.baseUrl, ride) // TODO this.baseUrl + '/xxx' possible
  }


}
