import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogComponent } from '../dialog/dialog.component';
import { Item } from '../model/Item';
import { MyrouteService } from '../myServices/myroute.service';
import { RestaurantService } from '../myServices/restaurant.service';

export interface DialogData {
  itemName:string;
  itemId:string;
  imageUrl:string;
  price: number;
  restaurantId:string;
}

@Component({
  selector: 'app-restaurant-user',
  templateUrl: './restaurant-user.component.html',
  styleUrls: ['./restaurant-user.component.css']
})
export class RestaurantUserComponent implements OnInit {

  imageSrc = ''  
  resName = '';

  quantity: string | undefined;
  name: string | undefined;
  searchText:string=""

  itemLists : Array<Item> = [];
  constructor(private restservice: RestaurantService,private snackobj:MatSnackBar,private dialog: MatDialog, private routeserv:MyrouteService) { }

  ngOnInit(): void {

    this.restservice.getRestaurantById(sessionStorage.getItem('restaurantId')).subscribe(
      (res:any)=>{
          this.resName = res.restaurantName;
          this.imageSrc = res.restaurantImageUrl;
      },
      (err:any)=>{
        console.log(err.errormessage);
      }
    )

    this.restservice.getAllRestaurantItems(sessionStorage.getItem('restaurantId')).subscribe(
      (res:any)=>{
      
        this.itemLists = res;
        console.log("Show list")
      },
      (err:any)=>{
        console.log(err)
      }
    )
  }

  getListall(){
    this.restservice.getAllRestaurantItems(sessionStorage.getItem('restaurantId')).subscribe(
      (res:any)=>{
        this.itemLists = res;
        console.log("Show all items")
      },
      (err:any)=>{
        console.log(err)
      }
    )
  }

  getListstarter(){
    this.restservice.getRestaurantItemCategory(sessionStorage.getItem('restaurantId'),"starter").subscribe(
      (res:any)=>{
        this.itemLists = res;
        console.log("Show list")
      },
      (err:any)=>{
        console.log(err)
      }
    )
  }

  getListsalad(){
    this.restservice.getRestaurantItemCategory(sessionStorage.getItem('restaurantId'),"salad").subscribe(
      (res:any)=>{
        this.itemLists = res;
        console.log("Show salad items")
      },
      (err:any)=>{
        console.log(err)
      }
    )
  }
  getListdessert(){
    this.restservice.getRestaurantItemCategory(sessionStorage.getItem('restaurantId'),"dessert").subscribe(
      (res:any)=>{
        this.itemLists = res;
        console.log("Show dessert items")
      },
      (err:any)=>{
        console.log(err)
      }
    )
  }
  getListspecial(){
    this.restservice.getRestaurantItemCategory(sessionStorage.getItem('restaurantId'),"main").subscribe(
      (res:any)=>{
        this.itemLists = res;
        console.log("Show special items")
      },
      (err:any)=>{
        console.log(err)
      }
    )
  }

  chooseQty(){
    this.snackobj.open("Item","Added to Cart", {
      duration: 2000,
    });
  }

  openDialog(res:Item): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '400px',
      data: {itemName: res.itemName, itemId: res.itemId, imageUrl: res.imageUrl, price: res.price, restaurantId: sessionStorage.getItem('restaurantId')},
    });

    dialogRef.afterClosed().subscribe(result => {
      // this.quantity = result;
    });
  }
}
