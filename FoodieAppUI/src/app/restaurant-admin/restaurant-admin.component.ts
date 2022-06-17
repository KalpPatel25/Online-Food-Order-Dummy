import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Restaurant } from '../model/Restaurant';
import { RestaurantService } from '../myServices/restaurant.service';

@Component({
  selector: 'app-restaurant-admin',
  templateUrl: './restaurant-admin.component.html',
  styleUrls: ['./restaurant-admin.component.css']
})
export class RestaurantAdminComponent implements OnInit {


  checked?:boolean;
  resList : Array<Restaurant> = [];
  status?:boolean
  myModel:boolean=false;
 
  constructor(private restservice : RestaurantService) { }

  ngOnInit(): void {
    
    this.restservice.getAllAdminRestaurants().subscribe(
      (res:any)=>{
        this.resList = res;   
      console.log("Show restaurant")
      },
      (err:any)=>{
        console.log(err)
      }
    )
  }

  autoRenew = new FormControl();
  onChange(value:any,restaurant:Restaurant) {
    if(value.checked===true){
      console.log(value.checked+"from true")
      this.status=true
  
    }
      else{
        console.log(this.checked+"from true")
        this.status=false
      
      }
      console.log(this.status+"from out")
      this.restservice.updateRestaurantStatus(restaurant.restaurantId,this.status).subscribe(
        (res:any)=>{
          console.log(res)
        },
        (err:any)=>{
          console.log(err.error)
        }
      )
    
    }
  
 


}
