import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { AuthGuard } from './auth.guard';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { HomeComponent } from './home/home.component';
import { LogInComponent } from './log-in/log-in.component';
import { CartComponent } from './main/cart/cart.component';
import { FavoriteComponent } from './main/favorite/favorite.component';
import { MainComponent } from './main/main.component';
import { ProfileComponent } from './main/profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { AddcusineComponent } from './vendor/addcusine/addcusine.component';
import { AddresturantComponent } from './vendor/addresturant/addresturant.component';
import { VendorComponent } from './vendor/vendor.component';
import { ViewRestaurantComponent } from './vendor/view-restaurant/view-restaurant.component';
import { ViewallcusinesComponent } from './vendor/viewallcusines/viewallcusines.component';

const routes: Routes = [
  {
    path:"",
    component:HomeComponent
  },
  {
    path:"login",
    component:LogInComponent
  },{
  path:"register",
  component:RegisterComponent
},{
  path:"vendor",
  
    component:VendorComponent
  },{
    path:"addcusine",
    component:AddcusineComponent
  },{
    path:"addresturant",
    component:AddresturantComponent
  },{
    path:"viewcusine",
    component:ViewallcusinesComponent
  },
  {
    path:"viewrestaurant",
    component:ViewRestaurantComponent
  },{
    path:"main",
    canActivate:[AuthGuard],
    component:MainComponent
  },{
    path:"profile",
    component:ProfileComponent
  },{
    path:"cart",
    canActivate:[AuthGuard],
    component:CartComponent
  },{
    path:"favourite",
    component:FavoriteComponent
  },{
    path:"about",component:AboutUsComponent
  },
  {path:"contact",component:ContactUsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
