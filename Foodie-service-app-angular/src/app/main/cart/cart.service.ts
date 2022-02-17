import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from 'src/app/model/Cart';
import { CusineDTO } from 'src/app/model/CusineDTO';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  baseUrl="http://localhost:9000/api/v4/";
  constructor(private http:HttpClient) { }


  addCartItem(email:string,id:number):Observable<object>{
    return this.http.post(`${this.baseUrl}`+"cart/"+ email+"/"+id,'');
  }
  deleteCartItem(id:number):Observable<object>{
    return this.http.delete(`${this.baseUrl}`+"cart/"+id);
  }
  getAllCartItemasByEmail(email:string):Observable<Cart[]>{
    return this.http.get<Cart[]>(`${this.baseUrl}`+"cart/"+email)
  }
 
}
