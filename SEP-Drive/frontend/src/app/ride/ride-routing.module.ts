import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RidePageComponent} from './components/ride-page/ride-page.component';

const routes: Routes = [
  {path: "requestride", component: RidePageComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RideRoutingModule {
}
