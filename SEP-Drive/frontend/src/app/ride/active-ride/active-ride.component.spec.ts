import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveRideComponent } from './active-ride.component';

describe('ActiveRideComponent', () => {
  let component: ActiveRideComponent;
  let fixture: ComponentFixture<ActiveRideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ActiveRideComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActiveRideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
