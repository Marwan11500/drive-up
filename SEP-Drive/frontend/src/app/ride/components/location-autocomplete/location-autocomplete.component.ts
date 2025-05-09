import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable, of} from 'rxjs';
import {debounceTime, distinctUntilChanged, map, switchMap, catchError} from 'rxjs/operators';
import {PlacesService} from '../../services/places.service';
import {Location} from '../../models/location.model';

@Component({
  selector: 'app-location-autocomplete',
  standalone: false,
  templateUrl: './location-autocomplete.component.html',
  styleUrls: ['./location-autocomplete.component.scss']
})
export class LocationAutocompleteComponent implements OnInit {
  @Input() label: string = '';
  @Input() placeholder: string = '';
  @Input() control!: FormControl;
  @Output() locationSelected = new EventEmitter<Location>();
  @Output() pinClicked = new EventEmitter<void>();

  filteredLocations!: Observable<Location[]>;

  constructor(private placesService: PlacesService) {
  }

  ngOnInit(): void {
    this.filteredLocations = this.control.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      map(value => typeof value === 'string' ? value : value?.name || ''),
      switchMap(query => this.onSearch(query))
    );
  }

  onSearch(query: string): Observable<Location[]> {
    if (!query.trim()) return of([]);
    return this.placesService.searchPlaces(query).pipe(
      catchError(() => of([]))
    );
  }

  displayFn(location: Location | string): string {
    if (typeof location === 'string') return location;
    return location ? location.name || location.address || '' : '';
  }

  onLocationSelected(location: Location) {
    this.locationSelected.emit(location);
  }
}
