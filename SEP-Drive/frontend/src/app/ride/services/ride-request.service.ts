import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Ride} from '../models/ride.model';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RideRequestService {

  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/api/ride-requests';
  }

  public submitRide(ride: any) {
    return this.http.post<Ride>(this.baseUrl, ride)
  }

  public getRide(username: string) {
    return this.http.get<Ride>(this.baseUrl + '/' + username)
  }

  public deactivateRide(username: string) {
    return this.http.delete<Ride>(this.baseUrl + '/' + username)
  }

  public userHasActiveRide(username: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/${username}/has-active`);
  }

  private activeRideStatus = new BehaviorSubject<boolean>(false);

  public activeRideStatus$ = this.activeRideStatus.asObservable();

  updateActiveRideStatus(username: string): void {
    this.userHasActiveRide(username).subscribe({
      next: status => this.activeRideStatus.next(status),
      error: err => console.error(err)
    });
  }

}
