import { Component, OnInit } from '@angular/core';
import { FormGroup, FormGroupDirective, FormBuilder, Validators, FormControl } from '@angular/forms';


import { MyrouteService } from '../myServices/myroute.service';
import { User } from '../model/User';
import { UserService } from '../myServices/user.service';
import { RestaurantService } from '../myServices/restaurant.service';
// import { MyrouteService } from '../myroute.service';
// import { AuthenticationService } from '../services/authentication.service';
// import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // Types: String[]= ["User", "Admin", "Restaurant owner"];
  user:User = new User();
  type:string="";
  token:string="";
  username : FormControl;
  password: FormControl;
  loginSuccess: boolean = false;
  public showPassword: boolean=false;
 
  errormessage: string="";
  

  constructor(private rouobj : MyrouteService,private userservice : UserService, private restService: RestaurantService) {
     
    
    this.username=new FormControl('', Validators.required);
    this.password=new FormControl('',[
      Validators.required,
      Validators?.maxLength(15),
      Validators?.minLength(5),
      Validators.pattern(/^(\s+\S+\s*)*(?!\s).*$/),
    ]
);
    }

  ngOnInit(): void {

    sessionStorage.clear();

  }

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  
   }



  onSubmit()
  {

 
  let mydata = {
    "username": this.username.value,
    "password": this.password.value,
    }

    console.log(mydata);

    if (this.username.invalid || this.password.invalid) {
      this.loginSuccess = false;
      this.errormessage = 'Provide username and password';
    }
else{
  
    
this.userservice.GenerateTokenfromServer(mydata).subscribe(
(result:any)=>
{
  this.token = result['token'];
  this.userservice.storeToken(this.token);
  this.errormessage="Successful";
  this.userservice.getUserType(this.username.value).subscribe(
    (userType:any)=>
    {
      this.user = userType;
      this.type = this.user.type;
      sessionStorage.setItem("userType",this.type);
      sessionStorage.setItem("username",userType.username);
      if(this.type==="User")
    this.rouobj.openHome();
    
  if(this.type==="Admin")
    this.rouobj.openRestaurantAdmin();
    
  if(this.type==="RestaurantOwner"){
    this.restService.getRestaurantId(this.username.value).subscribe(
      (res:any)=>
      {
        sessionStorage.setItem('restaurantId',res.restaurantId);
        this.rouobj.openRestaurantOwner();
      },
      (err:any)=>
      {
        console.log(err.errormessage);
      }
    )
    
  }
    
    },
    (err:any)=>{
      console.log(err);
    }
  )
  
},
(err:any)=>{
this.errormessage='Username Not Found';
console.log(err.error);
});

}
}
  
signUp()
{
  this.rouobj.openRegister();
}


}
