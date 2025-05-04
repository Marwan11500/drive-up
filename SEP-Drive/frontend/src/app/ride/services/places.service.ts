import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Location } from '../models/location.model';

@Injectable({
  providedIn: 'root'
})
export class PlacesService {
  private apiKey = 'AIzaSyBk_dCClCld6uiQIDcHjNDdkGiZo-p6qM4';
  private apiUrl = 'https://places.googleapis.com/v1/places:searchText';

  constructor(private http: HttpClient) {}

  searchPlaces(textQuery: string): Observable<Location[]> {
    const body = {
      textQuery: textQuery
    };

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'X-Goog-Api-Key': this.apiKey,
      'X-Goog-FieldMask': 'places.displayName,places.formattedAddress,places.location'
    });

    return new Observable<Location[]>(observer => {
      this.http.post<any>(this.apiUrl, body, { headers }).subscribe({
        next: response => {
          const locations: Location[] = response.places?.map((place: any) => ({
            name: place.displayName?.text,
            address: place.formattedAddress,
            latitude: place.location?.latitude,
            longitude: place.location?.longitude
          })) || [];

          observer.next(locations);
          observer.complete();
        },
        error: err => observer.error(err)
      });
    });
  }
}
