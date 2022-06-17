import { Component, OnInit } from '@angular/core';
import { Order } from '../model/Order';
import { OrderService } from '../myServices/order.service';
import { RestaurantService } from '../myServices/restaurant.service';

@Component({
  selector: 'app-order-user',
  templateUrl: './order-user.component.html',
  styleUrls: ['./order-user.component.css']
})
export class OrderUserComponent implements OnInit {

  orderPresent?:boolean
  orderList:Order[]=[];
  restaurantName:string="";
  constructor(private orderService: OrderService, private restService: RestaurantService) { }

  ngOnInit(): void {
    console.log(sessionStorage.getItem('username'))
    this.orderService.viewOrdersUser(sessionStorage.getItem('username')).subscribe(
      (res:any)=>{
        this.orderPresent=true;
        this.orderList=res;
        console.log("Show list");
      },
      (err:any)=>{
        this.orderPresent=false;
        console.log("error here")
        console.log(err)
      }
    )
  }

}
