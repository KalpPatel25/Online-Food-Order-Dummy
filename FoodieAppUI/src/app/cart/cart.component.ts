import { Component, OnInit } from '@angular/core';
import { CartItem } from '../model/CartItem';
import { Order } from '../model/Order';
import { MyrouteService } from '../myServices/myroute.service';
import { OrderService } from '../myServices/order.service';
import { RestaurantService } from '../myServices/restaurant.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {


  itemList:CartItem[]=[];
  orderId:String="tempOrder"+sessionStorage.getItem('username');
  public totalPrice:number=0.0;
  order?:Order;
  item:CartItem= new CartItem();
  public subtotal:number=0.0;
  public tax:number=0.0;
  orderPresent?:boolean;


  


  constructor(private rouobj: MyrouteService,private orderService: OrderService, private restService: RestaurantService) { }

  ngOnInit(): void {
    this.orderService.getAllItems(sessionStorage.getItem('username')).subscribe(
      (res:any)=>{
        if(res[0].itemName==null){
          this.orderPresent=false;
          this.subtotal=0;
          this.tax=0;
          this.totalPrice=0;
        }
        else{
          this.orderPresent=true;
         res.sort(function(a:any, b:any){
          if(a.itemName < b.itemName) { return -1; }
          if(a.itemName > b.itemName) { return 1; }
          return 0;
      })
      this.itemList=res;
        this.orderService.getOrderUser(this.orderId).subscribe(
          (res:any)=>{
            this.order=res;
            this.totalPrice=res.totalPrice;
            this.subtotal=res.amount;
            this.tax=res.tax;
          },
          (err:any)=>{
            console.log(err)
          }
        )
        }
      },
      (err:any)=>{
        console.log(err)
      }
    
    )
    
    
  }

  goToHome()
{
  this.rouobj.openHome();
}

placeOrder(){
  this.item = this.itemList[0];


  let order = {
    "orderId" : this.orderId,
    "restaurantId": this.item.restaurantId,
    "itemList" :this.itemList,
    "totalPrice":this.totalPrice,
    "username":sessionStorage.getItem('username'),
    
  }
  console.log(order);

  this.orderService.placeAnOrder(order).subscribe(
    (res:any)=>{
      this.rouobj.openOrderComplete();
      // console.log('success')
    },
    (err:any)=>{
      console.log(err.errormessage)
    }
  )
}

quantDecrease(item:CartItem)
{
  
  let itemQuantity =  item.itemQuantity-1
  let itemId = item.itemId;
  this.orderService.updateQuantity(itemId,itemQuantity,sessionStorage.getItem('username')).subscribe(
    (res:any)=>{
      this.ngOnInit();
    },
    (err:any)=>{
      this.ngOnInit();
      console.log(err)
    }
  )
}

quantIncrease(item:CartItem)
{
  let itemQuantity =  item.itemQuantity+1;
  let itemId = item.itemId;
  this.orderService.updateQuantity(itemId,itemQuantity,sessionStorage.getItem('username')).subscribe(
    (res:any)=>{
      this.ngOnInit();
    },
    (err:any)=>{
      console.log(err)
    }
  )
}

goToRestaurantPage()
{
  if(sessionStorage.getItem('restaurantId')==null)
    this.rouobj.openHome();
  else
    this.rouobj.openRestaurantUser();
}

removeItem(itemId:any)
{
  console.log(itemId);
  this.orderService.removeItem(itemId,sessionStorage.getItem('username')).subscribe(
    (res:any)=>{
      console.log(res);
      this.ngOnInit();
      
    },
    (err:any)=>{
      this.ngOnInit();
      console.log(err)
    }
  )
  window.location.reload();
}
}
