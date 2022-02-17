import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Cart } from 'src/app/model/Cart';
import { CusineDTO } from 'src/app/model/CusineDTO';
import { UserService } from 'src/app/user.service';
import { CartService } from './cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  userEmail!:string
  cartItems:Cart[]=[];
  CusinesById:CusineDTO[]=[];
  itemCount:number=0;
  total:number=0;
  cusineObj!:CusineDTO;
  image:any[]=[];
  constructor(private userService:UserService,private cartservice:CartService,private router:Router) { }

  ngOnInit(): void {
    this.userEmail=this.userService.userEmail;
    this.cartservice.getAllCartItemasByEmail(this.userEmail).subscribe(data=>{
      data.forEach(element=>{this.getCusineById(element.cusineID)});
      this.cartItems=data;
      console.log(this.cartItems);
      
    });

    //this.itemCount=this.cartItems.length;
    this.CusinesById.forEach(element => {
      this.total=element.cusineCost+this.total;
    });
    console.log("count"+this.itemCount);
    console.log("total"+this.total);
    console.log("**************"+this.CusinesById);

  }
  
  
getCusineById(id:number){

  this.userService.getCusineById(id).subscribe(data=>{
    this.cusineObj=data;
    console.log(this.cusineObj);
    this.total=this.total+this.cusineObj.cusineCost;
    this.itemCount=this.itemCount+1;
    this.CusinesById.push(this.cusineObj)
    this.CusinesById.forEach(z=>{
      this.userService.getUserImage(z.cusineImage).subscribe(y=>{
        this.image.push(y.url);
        console.log(y.url)
      })
    })
  })
 
 

}
deleteCartItem(id:number){
this.cartservice.deleteCartItem(id).subscribe(data=>{
  console.log(data)
})
this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
  this.router.navigate(['cart']);
});
this.image=[];
this.CusinesById=[];
}
checkout(){
  alert("order was plased")
  
}
}
