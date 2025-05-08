import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { RideModule } from './ride/ride.module';
import {ReactiveFormsModule} from '@angular/forms';
import { RegisterComponent } from './auth/register/register.component';
import { TwoFactorComponent } from './auth/two-factor/two-factor.component';
import {MatRadioButton, MatRadioGroup} from '@angular/material/radio';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatIconModule } from '@angular/material/icon';
import {MatButton, MatIconButton} from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import {
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerModule,
  MatDatepickerToggle
} from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    TwoFactorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    RideModule,
    FormsModule,
    MatRadioGroup,
    MatRadioButton,
    ReactiveFormsModule,
    MatFormField,
    MatLabel,
    MatFormField,
    MatInput,
    MatLabel,
    MatFormField,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatIconModule,
    MatIconButton,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatDatepicker,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatButton
  ],

  providers: [
    provideHttpClient(withInterceptorsFromDi()),
    provideNativeDateAdapter()
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
