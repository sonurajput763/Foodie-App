import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Cusine } from 'src/app/model/Cusine';
import { CusineDTO } from 'src/app/model/CusineDTO';
import { UserService } from 'src/app/user.service';
import { VendorService } from '../vendor.service';

@Component({
  selector: 'app-viewallcusines',
  templateUrl: './viewallcusines.component.html',
  styleUrls: ['./viewallcusines.component.css']
})
export class ViewallcusinesComponent implements OnInit {
  cusines: CusineDTO[] = []
  vendoremail!: string;
  image: any[] = [];
  constructor(private userservice: UserService, private vendorservice: VendorService, private route: Router) { }

  ngOnInit(): void {
    this.vendoremail = this.userservice.userEmail;
    this.vendorservice.getAllCusineByEmail(this.vendoremail).subscribe(data => {
      this.cusines = data;
      this.cusines.forEach(x => {
        this.userservice.getUserImage(x.cusineImage).subscribe(y => {
          this.image.push(y.url);
          console.log(y.url);
        })
      })
    })
  }
  deletecusine(id: number) {
    this.vendorservice.deleteCusine(id).subscribe(data => {
      console.log(data);
    });
    this.route.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.route.navigate(['viewcusine']);
    });
    this.image=[];
  }

}
