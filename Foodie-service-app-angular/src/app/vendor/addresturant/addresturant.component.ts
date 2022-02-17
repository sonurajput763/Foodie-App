import { Component, OnInit } from '@angular/core';
import { Restaurant } from 'src/app/model/Restaurant';
import { UserService } from 'src/app/user.service';
import { VendorService } from '../vendor.service';

@Component({
  selector: 'app-addresturant',
  templateUrl: './addresturant.component.html',
  styleUrls: ['./addresturant.component.css']
})
export class AddresturantComponent implements OnInit {
  restaurant:Restaurant=new Restaurant();
  vendoremail!:string;
  image!:any;
  constructor(private userservice:UserService,private vendorservice:VendorService) { }

  ngOnInit(): void {
    this.vendoremail=this.userservice.userEmail
  }

  onChange(event:any)
  {
    this.image=event.target.files[0];
  }
  saveRestaurant(){
    this.vendorservice.postImage(this.image).subscribe(data=>console.log(data));
    this.restaurant.email=this.vendoremail;
    this.vendorservice.saveResturants(this.restaurant).subscribe(data=>{
      console.log(data)
    })
    alert("Restaurant Added");
    this.image=null;
  }
}
