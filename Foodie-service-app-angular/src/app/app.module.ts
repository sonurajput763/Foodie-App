import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LogInComponent } from './log-in/log-in.component';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { VendorComponent } from './vendor/vendor.component';
import { AddcusineComponent } from './vendor/addcusine/addcusine.component';
import { AddresturantComponent } from './vendor/addresturant/addresturant.component';
import { ViewallcusinesComponent } from './vendor/viewallcusines/viewallcusines.component';
import { MainComponent } from './main/main.component';
import { ProfileComponent } from './main/profile/profile.component';
import { CartComponent } from './main/cart/cart.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialogModule} from '@angular/material/dialog';
import { ViewRestaurantComponent } from './vendor/view-restaurant/view-restaurant.component';
import {MatTabsModule} from '@angular/material/tabs';
import {MatSelectModule} from '@angular/material/select';
import { FavoriteComponent } from './main/favorite/favorite.component';
import { NavComponent } from './nav/nav.component';
import { DemoComponent } from './demo/demo.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { ContactNavComponent } from './contact-nav/contact-nav.component';

// import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LogInComponent,
    HomeComponent,
    VendorComponent,
    AddcusineComponent,
    AddresturantComponent,
    ViewallcusinesComponent,
    MainComponent,
    ProfileComponent,
    CartComponent,
    ViewRestaurantComponent,
    FavoriteComponent,
    NavComponent,
    DemoComponent,
    AboutUsComponent,
    ContactUsComponent,
    ContactNavComponent

 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatSelectModule,
    MatTabsModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
