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

  private activeRideSubjects: { [username: string]: BehaviorSubject<boolean> } = {};

  getActiveRide(username: string): Observable<boolean> {
    if (!this.activeRideSubjects[username]) {
      const stored = this.getStoredActiveRide(username);
      this.activeRideSubjects[username] = new BehaviorSubject<boolean>(stored);
    }
    return this.activeRideSubjects[username].asObservable();
  }

  setActiveRide(value: boolean, username: string): void {
    if (!this.activeRideSubjects[username]) {
      this.activeRideSubjects[username] = new BehaviorSubject<boolean>(value);
    } else {
      this.activeRideSubjects[username].next(value);
    }
    localStorage.setItem(`activeRide-${username}`, JSON.stringify(value));
  }

  getStoredActiveRide(username: string): boolean {
    const stored = localStorage.getItem(`activeRide-${username}`);
    return stored ? JSON.parse(stored) : false;
  }

}
