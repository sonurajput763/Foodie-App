import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cusine } from '../model/Cusine';
import { CusineDTO } from '../model/CusineDTO';
import { Restaurant } from '../model/Restaurant';
import { RestaurantDTO } from '../model/RestaurantDTO';

@Injectable({
  providedIn: 'root'
})
export class VendorService {

  vendorurl="http://localhost:9000/api/v2/"
  constructor(private http:HttpClient) { }

  saveCusine(cusine:Cusine):Observable<object>{
    console.log(cusine);
    return this.http.post(`${this.vendorurl}`+"cusine",cusine)
  }
  saveResturants(resturant:Restaurant):Observable<object>{
    return this.http.post(`${this.vendorurl}`+"restaurant",resturant)
  }
  getAllCusineByEmail(email:string):Observable<CusineDTO[]>{
    return this.http.get<CusineDTO[]>(`${this.vendorurl}`+"cusine/"+email)
  }
  getAllResturantByEmail(resturant:string):Observable<RestaurantDTO[]>{
    return this.http.get<RestaurantDTO[]>(`${this.vendorurl}`+"restaurant/"+resturant)
  }
  deleteCusine(id:number):Observable<object>{
    return this.http.delete(`${this.vendorurl}`+"cusine/"+id)
  }
  deleteResturant(id:number):Observable<object>{
    return this.http.delete(`${this.vendorurl}`+"restaurant/"+id)
  }
  postImage(file: File): Observable<object> {

    var formData = new FormData();

    // Store form name as "file" with file data
    formData.append("file", file);
    return this.http.post(`${this.vendorurl}` + "image/upload", formData)
  }
  postImage2(file: File): Observable<object> {

    var formData = new FormData();

    // Store form name as "file" with file data
    formData.append("file", file);
    return this.http.post(`${this.vendorurl}` + "image/upload", formData)
  }

}

