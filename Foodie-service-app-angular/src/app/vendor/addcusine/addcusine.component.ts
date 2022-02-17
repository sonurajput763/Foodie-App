import { error } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { Cusine } from 'src/app/model/Cusine';
import { Restaurant } from 'src/app/model/Restaurant';
import { UserService } from 'src/app/user.service';
import { VendorService } from '../vendor.service';

@Component({
  selector: 'app-addcusine',
  templateUrl: './addcusine.component.html',
  styleUrls: ['./addcusine.component.css']
})
export class AddcusineComponent implements OnInit {
  cusine:Cusine=new Cusine();
  restaurants:Restaurant[]=[];
  vendorEmail:string="";
  constructor(private venderservice:VendorService,private usserservice:UserService) { }
  cusineImage!:any;

  ngOnInit(): void {
    
    this.usserservice.getAllRestarunts().subscribe(data=>{
      this.restaurants=data;
      this.vendorEmail=this.usserservice.userEmail;
    })
  }
  onChange(event:any)
  {
    this.cusineImage=event.target.files[0];
  }

  saveCusine(){
    this.venderservice.postImage2(this.cusineImage).subscribe(data=>console.log(data)
    )
    this.cusine.email=this.vendorEmail;
    this.venderservice.saveCusine(this.cusine).subscribe(data=>{
      console.log(data)
    })
    alert("Cuisine saved");
    this.cusineImage=null;
  }
  addCusineBody()
  {
    
  }
}
