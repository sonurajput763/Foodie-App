import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Cusine } from '../model/Cusine';
import { CusineDTO } from '../model/CusineDTO';
import { RestaurantDTO } from '../model/RestaurantDTO';
import { UserService } from '../user.service';
import { CartService } from './cart/cart.service';
import { FavoriteService } from './favorite/favorite.service';
import { ProfileComponent } from './profile/profile.component';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  cusines: CusineDTO[] = [];
  restarunts: RestaurantDTO[] = [];
  searchinput: any;
  cusinesToDisplay: CusineDTO[] = [];
  cityname!: any;
  userEmail!: string;
  arrRestaruntTab: RestaurantDTO[] = [];
  constructor(private service: UserService, private dialog: MatDialog, private cartservice: CartService, private favouriteservice: FavoriteService) { }
  image: any = [];
  image2: any = [];
  oncityShowCusinesAndResturants(event: any) {
    this.cityname = event;

    if (this.cityname !== '') {
      this.cusinesToDisplay = [];
      this.arrRestaruntTab = [];
      this.image=[];
      this.image2=[];
    }
    this.service.getAllCusinesByCity(this.cityname).subscribe(data => {
      data.forEach(element => {
        this.cusinesToDisplay.push(element)
      });
    })
    this.service.getAllresturantsByCity(this.cityname).subscribe(data => {
      data.forEach(element => {
        this.arrRestaruntTab.push(element)
      });

    })

  }
  addCusineToCart(id: number) {
    this.cartservice.addCartItem(this.userEmail, id).subscribe(data => {
      console.log(data);
    })

  }

  findstartswith(inputlist: any, searchkey: string, inputstring: string) {

    for (var i = 0; i < inputlist.length; i++) {
      if (inputlist[i][searchkey].startsWith(inputstring)) {
        this.cusinesToDisplay.push(inputlist[i]);
      }
    }
    return i;
  }
  onSearchCusine(search: any) {
    if (search.form.value.searchinput !== '') {
      this.cusinesToDisplay = [];
      this.image=[];
    }
    console.log(search.form.value.searchinput);
    console.log(this.cusines);
    this.findstartswith(this.cusines, "cusineName", search.form.value.searchinput);
    // debugger
    // this.cusinesToDisplay = this.cusines.startsWith(search.form.value.searchinput);}, search.form.value.searchinput)

  }
  profilelog() {
    const dialogRef = this.dialog.open(ProfileComponent);
    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(result);
    });
  }
  ngOnInit(): void {
    this.dialog.closeAll()
    this.userEmail = this.service.userEmail;
    this.service.getAllCusine().subscribe(data => {
      this.cusines = data
      this.cusinesToDisplay = this.cusines;
      this.cusinesToDisplay.forEach(cuisine => {

        this.service.getUserImage(cuisine.cusineImage).subscribe(x => { console.log(x.url); this.image.push(x.url) });

      })
    })
    this.service.getAllRestarunts().subscribe(data => {
      this.restarunts = data;
      this.arrRestaruntTab = data;
      this.arrRestaruntTab.forEach(restaurant => {
        this.service.getUserImage(restaurant.logo).subscribe(x => { this.image2.push(x.url) });
      })
    })
  }
  addToFavouriteCusine(id: number) {
    this.favouriteservice.addCusineToFavorit(this.service.userEmail, id).subscribe(data => {
      console.log(data)
    })
  }
  addRestaurantToFavourite(id: number) {
    this.favouriteservice.addResturantToFavourite(this.service.userEmail, id).subscribe(data => {
      console.log(data)
    })
  }

}

