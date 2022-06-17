import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,BehaviorSubject } from "rxjs";
import { Order } from '../model/Order';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  order : Array<Order>=[];

  constructor(private httpcli: HttpClient, private userService: UserService) {
    
   }


   getAllItems(username:any):any
   {
     let tok = this.userService.getToken();
     return this.httpcli.get(`http://localhost:8086/foodie/orderCart/viewAllItems/${username}`,
     {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
   }

   getOrderUser(orderId:any):any{
    let tok = this.userService.getToken();
     return this.httpcli.get(`http://localhost:8086/foodie/order/viewOrder/${orderId}`,
     {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
   }

   placeAnOrder(order:any)
   {
    let tok = this.userService.getToken();
     return this.httpcli.post(`http://localhost:8086/foodie/order/placeOrder`,order,
     {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
   }

   updateQuantity(itemId:any,itemQuantity:any, username:any)
   {
    let tok = this.userService.getToken();
     return this.httpcli.put(`http://localhost:8086/foodie/orderCart/updateQty/${itemId}/${itemQuantity}/${username}`,{},
     {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
   }

   removeItem(itemId:any,username:any):Observable<String>
   {
    let tok = this.userService.getToken();
     return this.httpcli.delete<String>(`http://localhost:8086/foodie/orderCart/removeFromCart/${itemId}/${username}`,{
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
   }

   viewOrdersOwner(restaurantId:any)
   {
    let tok = this.userService.getToken();
     return this.httpcli.get(`http://localhost:8086/foodie/restaurantOwner/viewAllOrders/${restaurantId}`,
     {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
   }

   viewOrdersUser(username:any)
   {
    let tok = this.userService.getToken();
     return this.httpcli.get(`http://localhost:8086/foodie/restaurantUser/viewAllOrders/${username}`,
     {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
   }

   viewOrdersAdmin()
   {
    let tok = this.userService.getToken();
     return this.httpcli.get(`http://localhost:8086/foodie/Admin/viewAllOrders`,
     {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
   }

   addItemToCart(item:any, username:any)
   {
    let tok = this.userService.getToken();
    return this.httpcli.post(`http://localhost:8086/foodie/orderCart/addToCart/${username}`,item,
    {
      headers: new HttpHeaders().set('Authorization',`Bearer ${tok}`)
    });
   }

}
