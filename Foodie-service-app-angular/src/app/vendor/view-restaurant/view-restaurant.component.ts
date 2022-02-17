import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { RestaurantDTO } from 'src/app/model/RestaurantDTO';
import { UserService } from 'src/app/user.service';
import { VendorService } from '../vendor.service';

@Component({
  selector: 'app-view-restaurant',
  templateUrl: './view-restaurant.component.html',
  styleUrls: ['./view-restaurant.component.css']
})
export class ViewRestaurantComponent implements OnInit {
  restaurants:RestaurantDTO[]=[];
  image:any[]=[];
 
  constructor(private userservice:UserService,private vendorservice:VendorService,private route:Router) { }

  ngOnInit(): void {
    this.vendorservice.getAllResturantByEmail(this.userservice.userEmail).subscribe(data=>{
      this.restaurants=data;
      this.restaurants.forEach(x=>{
        this.userservice.getUserImage(x.logo).subscribe(y=>{
          this.image.push(y.url);
          console.log(y.url);
        })
      })
    })
  }
  deleterestaurant(id:number){
    this.vendorservice.deleteResturant(id).subscribe(data=>{
      console.log(data)
    })
    this.route.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.route.navigate(['viewrestaurant']);
    });
    this.image=[];
  }
}
