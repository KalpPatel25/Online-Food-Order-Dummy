import { HttpStatusCode } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CartItem } from '../model/CartItem';
import { OrderService } from '../myServices/order.service';
import { DialogData } from '../restaurant-user/restaurant-user.component';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent {

  quantity:number=1;
  constructor(
    public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, private orderService: OrderService, private snackobj: MatSnackBar) {}

  quantDecrease()
{
  this.quantity =this.quantity-1;
}

quantIncrease()
{
  this.quantity =this.quantity+1;
}

AddToCart(data: any)
{
  let item =
  {
    "itemName": data.itemName,
    "itemId" : data.itemId,
    "price":data.price,
    "itemQuantity":this.quantity,
    "restaurantId":data.restaurantId,
    "imageUrl":data.imageUrl
  }

  this.orderService.addItemToCart(item,sessionStorage.getItem('username')).subscribe(
    (res:any)=>
    {
      if(res.HttpStatusCode==404){
        this.snackobj.open("Error","One order can only have items from one restaurant", {
          duration: 5000,
        });
      }
      console.log(res);
      this.snackobj.open("Item","Added to Cart", {
        duration: 2000,
      });
      window.location.reload();
    },
    (err:any)=>
    {
      this.snackobj.open("Error","One order can only have items from one restaurant", {
        duration: 5000,
      });
      console.log(err.errormessage);
    }
  )
}

}
