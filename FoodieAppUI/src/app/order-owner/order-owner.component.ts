import { Component, OnInit } from '@angular/core';
import { Order } from '../model/Order';
import { OrderService } from '../myServices/order.service';

@Component({
  selector: 'app-order-owner',
  templateUrl: './order-owner.component.html',
  styleUrls: ['./order-owner.component.css']
})
export class OrderOwnerComponent implements OnInit {

  orderPresent?:boolean
  orderList:Order[]=[];
  restaurantName:string="";
  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    console.log(sessionStorage.getItem('restaurantId'))
    this.orderService.viewOrdersOwner(sessionStorage.getItem('restaurantId')).subscribe(
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