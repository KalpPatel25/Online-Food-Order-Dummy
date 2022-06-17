import { Component, OnInit } from '@angular/core';
import { Order } from '../model/Order';
import { OrderService } from '../myServices/order.service';

@Component({
  selector: 'app-order-admin',
  templateUrl: './order-admin.component.html',
  styleUrls: ['./order-admin.component.css']
})
export class OrderAdminComponent implements OnInit {

  orderPresent?:boolean
  orderList:Order[]=[];
  restaurantName:string="";
  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    console.log(sessionStorage.getItem('username'))
    this.orderService.viewOrdersAdmin().subscribe(
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
