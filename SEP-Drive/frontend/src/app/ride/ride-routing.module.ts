import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RideFormComponent } from './components/ride-form/ride-form.component';

const routes: Routes = [
  { path: 'ride-form', component: RideFormComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RideRoutingModule { }
