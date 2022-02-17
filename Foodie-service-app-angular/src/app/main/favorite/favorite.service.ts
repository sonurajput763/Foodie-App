import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { observableToBeFn } from 'rxjs/internal/testing/TestScheduler';
import { Favourite } from 'src/app/model/Favourite';

@Injectable({
  providedIn: 'root'
})
export class FavoriteService {
  baseUrl="http://localhost:9000/api/v5/";
  constructor(private http:HttpClient) { }


  addCusineToFavorit(email:string,id:number):Observable<object>{
    return this.http.post(`${this.baseUrl}`+"cusine/"+email+"/"+id,'')
  }
  addResturantToFavourite(email:string,id:number):Observable<object>{
    return this.http.post(`${this.baseUrl}`+"restaurant/"+email+"/"+id,'')
  }
  getAllFavouriteByEmail(email:string):Observable<Favourite>{
    return this.http.get<Favourite>(`${this.baseUrl}`+"favourite/"+email)
  }
  deleteCusineFromFavourite(email:string,id:number):Observable<object>{
    return this.http.delete(`${this.baseUrl}`+"favourite/cusine/"+email+"/"+id)
  }
  deleteRestaurantFromFavourite(email:string,id:number):Observable<object>{
    return this.http.delete(`${this.baseUrl}`+"favourite/restaurant/"+email+"/"+id)
  }
}
