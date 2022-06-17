import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FavouritesService } from '../myServices/favourites.service';
import { Restaurant } from '../model/Restaurant';
import { MyrouteService } from '../myServices/myroute.service';
import { RestaurantService } from '../myServices/restaurant.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {

  fontStyleControl = new FormControl();
  fontStyle?: string;

  addResFav?:boolean
  resList : Array<Restaurant> = [];
  resFavList : Array<Restaurant> = [];
  searchText:string="";

  
  constructor(private restservice : RestaurantService, private snackobj:MatSnackBar,private rouobj:MyrouteService, private favListservice: FavouritesService) { }

  ngOnInit(): void {

    this.restservice.getAllrestaurants().subscribe(
      (res:any)=>{
        this.resList = res;
        console.log("Show restaurant")
       
      },
      (err:any)=>{
        console.log("coming here");
        console.log(err)
      }
    )

    // if(restaurant.addToFav){
    //   restaurant.addToFav = false;
    // }
    // else{
    //   restaurant.addToFav = true;
    // }
    // this.addResFav = restaurant.addToFav;
  }
  onaddToFav(restaurant:Restaurant){

    if(sessionStorage.getItem('mytoken')==null)
      this.rouobj.openLogin();
    else{

    if(restaurant.addToFav){
      restaurant.addToFav = false;
    }
    else{
      restaurant.addToFav = true;
    }
    this.addResFav = restaurant.addToFav;

    if(this.addResFav){
      this.favListservice.addRestaurantToFav(restaurant,sessionStorage.getItem('username')).subscribe(
        (res:any)=>{
          this.snackobj.open("Restaurant","Added to Favourites", {
            duration: 2000,
          });
        },
        (err:any)=>{
          console.log("Error " + err)
        }
      )
    }
    else{
     
      this.favListservice.removeRestaurantFromFav(sessionStorage.getItem('username'),restaurant.restaurantId).subscribe(
        (res:any)=>{
          this.snackobj.open("Restaurant ","Removed from Favourites", {
            duration: 2000,
          });
        },
        (err:any)=>{
          console.log("Error " + err)
        }
      )
    }
  }

    
  }

  goTofav(){
    this.rouobj.openFavourite();
  }
  goToadminHome(){
    this.rouobj.openRestaurantAdmin();
  }
  goToMenu(res: Restaurant){
    let restaurantId = res.restaurantId;
    sessionStorage.setItem('restaurantId',restaurantId);
    this.rouobj.openRestaurantUser();
   
  }
  goToOwner(){
    this.rouobj.openOrderOwner();
  }
}
