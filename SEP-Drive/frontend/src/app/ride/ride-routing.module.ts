import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TesterComponent} from './components/tester/tester.component';

const routes: Routes = [
  { path: 'tester', component: TesterComponent } //TODO REMOVE
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RideRoutingModule { }
