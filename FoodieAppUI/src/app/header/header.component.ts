import { Component, OnInit } from '@angular/core';
import { MyrouteService } from '../myServices/myroute.service';
import { OrderService } from '../myServices/order.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  isAuth:Boolean=false;
  isUser:Boolean=false;
  isAdmin:Boolean=false;
  isOwner:Boolean=false;
  username:any;
  itemNumber:number=0;
  itemList:any[]=[];
  
  constructor(private rouobj: MyrouteService, private orderService: OrderService) { }

  ngOnInit(): void {
      // window.location.reload();
      if((sessionStorage.getItem('mytoken'))!=null){
        this.isAuth=true;
        this.username=sessionStorage.getItem('username');
        if((((sessionStorage.getItem('userType'))?.toUpperCase())==="USER"))
          this.isUser=true;
        if((((sessionStorage.getItem('userType'))?.toUpperCase())==="ADMIN"))
          this.isAdmin=true;
        if((((sessionStorage.getItem('userType'))?.toUpperCase())==="RESTAURANTOWNER"))
          this.isOwner=true;
      }
      this.username=sessionStorage.getItem('username');

      this.orderService.getAllItems(sessionStorage.getItem('username')).subscribe(
        (res:any)=>
        {
          if(res[0].itemName!=null){
          this.itemList=res;
          this.itemNumber=this.itemList.length;
          }
        },
        (err:any)=>
        {
          console.log(err.errormessage)
        }
      )
  }

  goToCart()
  {
    this.rouobj.openCart();
    
  }

  goToSignIn()
  {
    
    this.rouobj.openLogin();
    
  }
  goToSignUp()
  {
    this.rouobj.openResgisterRestaurant();
    
  }
  goToResSearch()
  {
    this.rouobj.openResSearch();
    
  }

  goToSignOut(){
    this.rouobj.openHome();
    sessionStorage.clear();
    this.isAuth=false;
    this.isAdmin=false;
    this.isOwner=false;
    this.isUser=false;
  }

  myFunction() {
    var x = document.getElementById("myTopnav")!;
    if (x.className === "topnav") {
      x.className += " responsive";
    } else {
      x.className = "topnav";
    }
  }
}
