import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RidePageComponent} from './components/ride-page/ride-page.component';
import {ActiveRidePageComponent} from './components/active-ride-page/active-ride-page.component';

const routes: Routes = [
  {path: "request", component: RidePageComponent},
  {path: "active", component: ActiveRidePageComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RideRoutingModule {
}
