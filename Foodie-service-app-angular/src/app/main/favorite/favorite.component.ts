import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { CusineDTO } from 'src/app/model/CusineDTO';
import { Favourite } from 'src/app/model/Favourite';
import { RestaurantDTO } from 'src/app/model/RestaurantDTO';
import { UserService } from 'src/app/user.service';
import { CartService } from '../cart/cart.service';
import { ProfileComponent } from '../profile/profile.component';
import { FavoriteService } from './favorite.service';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css']
})
export class FavoriteComponent implements OnInit {
  favourite!:Favourite;
  cusineObj!: CusineDTO;
  resturantObj!:RestaurantDTO;
  CusinesById: CusineDTO []=[] 
  resturants:RestaurantDTO[]=[];
  userEmail!:string;
  image:any[]=[];
  image2:any[]=[];
  constructor(private favouriteservice:FavoriteService,private userservice:UserService,private route:Router,private cartservice:CartService) { }

  ngOnInit(): void {

    this.favouriteservice.getAllFavouriteByEmail(this.userservice.userEmail).subscribe(data=>{
      this.favourite=data;
      data.cusines.forEach(element=>this.getCusineById(element))
      data.restaurants.forEach(element1=>this.getRestaurantById(element1))
      console.log(this.favourite)
      this.userEmail=this.userservice.userEmail;

    })
  }
  getCusineById(id:number){

    this.userservice.getCusineById(id).subscribe(data=>{
      this.cusineObj=data;
      console.log(this.cusineObj);
      this.CusinesById.push(this.cusineObj)
      this.CusinesById.forEach(x=>{
        this.userservice.getUserImage(x.cusineImage).subscribe(y=>{
          this.image.push(y.url);
          console.log(y.url);
        })
      })
    })
  }

  getRestaurantById(id:number){
    this.userservice.getRestaurantById(id).subscribe(data=>{
      this.resturantObj=data;
      console.log(this.resturantObj)
      this.resturants.push(this.resturantObj);
      this.resturants.forEach(a=>{
        this.userservice.getUserImage(a.logo).subscribe(b=>{
          this.image2.push(b.url);
        })
      })
    })
  }
  deleteCusine(id:number){
    this.favouriteservice.deleteCusineFromFavourite(this.userservice.userEmail,id).subscribe(data=>{
      console.log(data);
     
    })
    this.route.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.route.navigate(['favourite']);
    });
    this.image=[];
  }
  dalateRestaurant(id:number){
    this.favouriteservice.deleteRestaurantFromFavourite(this.userservice.userEmail,id).subscribe(data=>{
      console.log(data)
     
    })
    this.route.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.route.navigate(['favourite']);
  });
  this.image2=[];
  }
  addCusineToCart(id:number){
    this.cartservice.addCartItem(this.userEmail,id).subscribe(data=>{
      console.log(data);
    })
  }
}
