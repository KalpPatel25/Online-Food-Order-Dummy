import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class MyrouteService {

  
  restImg:string = '';
  restName:string = '';


  constructor(private rouobj: Router) { }

  openHome()
  {
    this.rouobj.navigate(['home']).then(() => {
      window.location.reload();
    });
  }

  openOrderComplete()
  {
    this.rouobj.navigate(['order-complete']).then(() => {
      window.location.reload();
    });
  }

  openInfoPage()
  {
    this.rouobj.navigate(['info-page'])
  }

  openCart()
  {
    this.rouobj.navigate(['cart']).then(() => {
      window.location.reload();
    });
  }

  openFavourite()
  {
    this.rouobj.navigate(['favourite']);
  }

  openLogin()
  {
    this.rouobj.navigate(['login']);
  }

  openOrderAdmin()
  {
    this.rouobj.navigate(['order-admin']);
  }

  openOrderOwner()
  {
    this.rouobj.navigate(['order-owner']);
  }

  openOrderUser()
  {
    this.rouobj.navigate(['order-user']);
  }

  openAboutUs()
  {
    this.rouobj.navigate(['about-us']);
  }

  openRegister()
  {
    this.rouobj.navigate(['register']);
  }

  openResgisterRestaurant()
  {
    this.rouobj.navigate(['register-restaurant']);
  }

  openRestaurantAdmin()
  {
    this.rouobj.navigate(['restaurant-admin']).then(() => {
      window.location.reload();
    });
  }

  openRestaurantOwner()
  {
    this.rouobj.navigate(['restaurant-owner']).then(() => {
      window.location.reload();
    });
  }

  openRestaurantUser()
  {
    this.rouobj.navigate(['restaurant-user']);
  }

  openResSearch()
  {
    this.rouobj.navigate(['res-search']);
  }
}
