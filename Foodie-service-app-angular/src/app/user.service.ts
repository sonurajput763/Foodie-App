import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { User } from './model/User';
import { UserAuth } from './model/UserAuth';
import { Restaurant } from './model/Restaurant';
import { Cusine } from './model/Cusine';
import { CusineDTO } from './model/CusineDTO';
import { RestaurantDTO } from './model/RestaurantDTO';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  userEmail!: string;
  accountType!:string;
  

  foodieUrl = "http://localhost:9000/api/v1/"
  authUrl = "http://localhost:9000/api/v3/"
  constructor(private httpClient: HttpClient) { }

  userprofile(file: File): Observable<object> {

    var formData = new FormData();

    // Store form name as "file" with file data
    formData.append("file", file);
    return this.httpClient.post(`${this.foodieUrl}` + "users/image", formData)
  }
  userResgister(user: User): Observable<object> {
    return this.httpClient.post(`${this.foodieUrl}` + "users/register", user)
  }
  authuserRegister(user: User): Observable<object> {
    return this.httpClient.post(`${this.authUrl}` + "register", user)
  }
  getAccountType(email:UserAuth):Observable<any>
  {
    return this.httpClient.post(`${this.authUrl}`+"user",email,{responseType:'text'});
  }
  login(userauth: UserAuth): Observable<object> {
    console.log(userauth)
    return this.httpClient.post(`${this.authUrl}` + "login", userauth)
  }
  loginUser(token: any) {
    localStorage.setItem("token", token.Token);
    return true;
  }
  isLoggedIn() {
    let token = localStorage.getItem("token");
    if (token == undefined || token === '' || token == null) {
      return false;
    }
    else {
      return true;
    }
  }
  saveEmail(email: string) {
    this.userEmail = email;
  }
  getuserByEmail(email: string): Observable<User> {
    return this.httpClient.get<User>(`${this.foodieUrl}` + "users/" + email)
  }
  getAllRestarunts(): Observable<RestaurantDTO[]> {
    return this.httpClient.get<RestaurantDTO[]>(`${this.foodieUrl}` + "restaurants");
  }
  getAllCusine(): Observable<CusineDTO[]> {
    return this.httpClient.get<CusineDTO[]>(`${this.foodieUrl}` + "cusine");
  }
  getAllCusinesByCity(city: string): Observable<CusineDTO[]> {
    return this.httpClient.get<CusineDTO[]>(`${this.foodieUrl}` + "cusine/" + city);
  }
  getAllresturantsByCity(city: string): Observable<RestaurantDTO[]> {
    return this.httpClient.get<RestaurantDTO[]>(`${this.foodieUrl}` + "restaurant/" + city);
  }
  getAllCusinesByName(name: string): Observable<CusineDTO[]> {
    return this.httpClient.get<CusineDTO[]>(`${this.foodieUrl}` + "cusine/all/" + name);
  }
  CusineAddToCart(email: string, id: number): Observable<object> {
    return this.httpClient.post(`${this.foodieUrl}` + "cart/" + email + "/" + id, "");
  }
  CusineAddToFavourite(email: string, id: number): Observable<object> {
    return this.httpClient.post(`${this.foodieUrl}` + "cusine/" + email + "/" + id, "");
  }
  restaurantAddToFavourite(email: string, id: number):Observable<object>{
    return this.httpClient.post(`${this.foodieUrl}` + "restaurant/" + email + "/" + id, "");
  }
  getCusineById(id:number):Observable<CusineDTO>{
    
    return this.httpClient.get<CusineDTO>(`${this.foodieUrl}` + "getcusine/" + id)
  }
  getRestaurantById(id:number):Observable<RestaurantDTO>{
    return this.httpClient.get<RestaurantDTO>(`${this.foodieUrl}` + "getrestaurant/" + id)
  }
  getUserImage(name:string):Observable<any>
  {
    return this.httpClient.get<any>(`${this.foodieUrl}`+"users/image/"+name,{observe:"response",responseType:"blob" as "json"});
  }
}
