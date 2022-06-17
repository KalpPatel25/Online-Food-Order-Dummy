import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from "rxjs";
import { Restaurant } from '../model/Restaurant';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  restaurant : Array<Restaurant>=[];
  resSubject : BehaviorSubject<Array<Restaurant>>;

  constructor(private httpcli : HttpClient, private authService : UserService) { 
    this.resSubject = new BehaviorSubject<Array<Restaurant>>([]);
  }

  fetchDatafromServer()
  {
    let tok=this.authService.getToken();
    return this.httpcli.get<Array<Restaurant>>('http://localhost:8080/foodie/viewRestaurant',
    {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    })
  }

  updateRestaurantStatus(restaurantId:any,status:any){
    let tok = this.authService.getToken();
    return this.httpcli.put(`http://localhost:8080/foodie/restaurantAdmin/updateResStatus/${restaurantId}`,status,{
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
  }

  getAllrestaurants() :any
  {
    return this.httpcli.get('http://localhost:8080/foodie/viewRestaurant');
    
  }

  getAllAdminRestaurants():any
  {
    let tok = this.authService.getToken();
    return this.httpcli.get('http://localhost:8080/foodie/restaurantAdmin/viewRes',{
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
  }

  
  getRestaurantByIdOwner(restaurantId:any){
    let tok = this.authService.getToken();
    return this.httpcli.get(`http://localhost:8080/foodie/restaurantOwner/viewRes/${restaurantId}`,{
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
  }


  getAllRestaurantItems(restaurantId:any){
    let tok = this.authService.getToken();
    return this.httpcli.get(`http://localhost:8080/foodie/restaurantUser/viewRes/${restaurantId}`,
    {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
  }

  getRestaurantItemCategory(restaurantId:any,category:any){
   let tok = this.authService.getToken();
    return this.httpcli.get(`http://localhost:8080/foodie/restaurantUser/viewCategory/${restaurantId}/${category}`,
    {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    })
  }

  updateRestaurantItemStatus(restaurantId:any, itemId:any, itemstatus:any){
    let tok = this.authService.getToken();
    return this.httpcli.put(`http://localhost:8080/foodie/restaurantOwner/updateItemStatus/${restaurantId}/${itemId}`,itemstatus,
    {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    })
  }

  registerNewRestaurant(restaurant:any){
    return this.httpcli.post(`http://localhost:8080/foodie/addRestaurant`,restaurant,
    );
  }

  getRestaurantId(username:any){
    return this.httpcli.get(`http://localhost:8080/foodie/viewRestaurantId/${username}`);
  }

  getRestaurantById(restaurantId:any)
  {
    let tok = this.authService.getToken();
    return this.httpcli.get(`http://localhost:8080/foodie/restaurant/getRestaurant/${restaurantId}`, {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    }
  );
  }
}



