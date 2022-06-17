import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../model/Restaurant';
import { MyrouteService } from '../myServices/myroute.service';
import { RestaurantService } from '../myServices/restaurant.service';

@Component({
  selector: 'app-res-search',
  templateUrl: './res-search.component.html',
  styleUrls: ['./res-search.component.css']
})
export class ResSearchComponent implements OnInit {

  searchText: any;
  favIcon: any = "favorite_border";
  
  resList : Array<Restaurant> = [];
  constructor(private rouobj: MyrouteService,private restservice : RestaurantService) { }

  ngOnInit(): void {
    this.restservice.getAllrestaurants().subscribe(
      (res:any)=>{
        this.resList = res;
        console.log(res);
      },
      (err:any)=>{
        console.log(err)
      }
    )
  }

  switchFav()
  {
    if(this.favIcon==="favorite_border")
      this.favIcon='favorite';
    if(this.favIcon==="favorite")
      this.favIcon="favourite_border";
  }

  goToRestaurantPage()
  {
    this.rouobj.openRestaurantUser();
  }

}
