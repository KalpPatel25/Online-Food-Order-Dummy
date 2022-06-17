import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FavouritesService } from '../myServices/favourites.service';
import { Restaurant } from '../model/Restaurant';
import { MyrouteService } from '../myServices/myroute.service';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {

  favLists : Array<Restaurant> = [];
  constructor(private favListservice: FavouritesService,private snackobj:MatSnackBar, private rouobj:MyrouteService) { }

  ngOnInit(): void {
    this.favListservice.getAllFavrestaurants(sessionStorage.getItem('username')).subscribe(
      (res:any)=>{
        this.favLists = res;
        console.log(res)
      },
      (err:any)=>{
        console.log(err)
      }
    )
  }
  onremoveFromFav(restaurant:Restaurant){
    
    
      restaurant.addToFav = false;
      console.log(restaurant)
      this.favListservice.removeRestaurantFromFav(sessionStorage.getItem('username'),restaurant.restaurantId).subscribe(
        (res:any)=>{
          
          this.snackobj.open("Restaurant ","Removed from Favourites", {
            duration: 2000,
          });
         
          window.location.reload();
          
        },
        (err:any)=>{
          console.log("Error " + err)
        }
      )
  }

  goToRestaurant(fav:Restaurant)
  {
    let restaurantId = fav.restaurantId;
    sessionStorage.setItem('restaurantId',restaurantId);
    this.rouobj.openRestaurantUser();
  }
  }

