import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Item } from '../model/Item';
import { MyrouteService } from '../myServices/myroute.service';
import { RestaurantService } from '../myServices/restaurant.service';

@Component({
  selector: 'app-restaurant-owner',
  templateUrl: './restaurant-owner.component.html',
  styleUrls: ['./restaurant-owner.component.css']
})
export class RestaurantOwnerComponent implements OnInit {

  resId = '';
  imageSrc = ''  
  resName = '';
  status?:boolean;
  itemLists : Array<Item> = [];
  itemPresent?:boolean;
  constructor(private restservice: RestaurantService,private snackobj:MatSnackBar, private routeserv:MyrouteService) { }

  ngOnInit(): void {

    this.restservice.getRestaurantByIdOwner(sessionStorage.getItem('restaurantId')).subscribe(
      (res:any)=>{

        if(res[0].itemId==null){
          this.itemPresent=false;
        }
        else{
          this.itemPresent=true;
          this.itemLists = res;
        }
        
          }
    )

    this.restservice.getRestaurantById(sessionStorage.getItem('restaurantId')).subscribe(
      (res:any)=>{
        this.imageSrc = res.restaurantImageUrl;
        this.resName = res.restaurantName;
      }
    )

    
  }

 
  onChange(value:any,item:Item) {

    if(value.checked===true){
      item.itemStatus = "Enable"; 
    }
      else{
        item.itemStatus = "Disable";
      }

      this.resId = sessionStorage.getItem('restaurantId')!
      console.log(this.status+"from out")
      this.restservice.updateRestaurantItemStatus(this.resId,item.itemId,item.itemStatus).subscribe(
        (res:any)=>{
          console.log(res)
        },
        (err:any)=>{
          console.log(err.error)
  
        }
  
      )
  
    }


}
