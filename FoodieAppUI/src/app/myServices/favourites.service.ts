import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Favourite } from '../model/Favourite';
import { Restaurant } from '../model/Restaurant';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class FavouritesService {

  
  resSubject : BehaviorSubject<Array<Restaurant>>;

  constructor(private httpcli : HttpClient, private authService : UserService) { 
    this.resSubject = new BehaviorSubject<Array<Restaurant>>([]);
  }

  addRestaurantToFav(res : Restaurant, userId: any) : Observable<any> {
    let tok = this.authService.getToken();
    return this.httpcli.post(`http://localhost:8082/fav/addfav/${userId}`,res
    ,{
      headers:new HttpHeaders().set('Authorization',`Bearer${tok}`)
    }
    )
  }

  getAllFavrestaurants(userId:any)
  {
    let tok =this.authService.getToken();
    return this.httpcli.get(`http://localhost:8082/fav/viewByUser/${userId}`
    ,{
      headers:new HttpHeaders().set('Authorization',`Bearer${tok}`)
    });
    
  }

  removeRestaurantFromFav(userId:any, restaurantId:any) : Observable<any>{
    let tok = this.authService.getToken();
    return this.httpcli.delete(`http://localhost:8082/fav/deleteByResId/${userId}/${restaurantId}`
    ,{
      headers:new HttpHeaders().set('Authorization',`Bearer${tok}`)
    })
  }
}
