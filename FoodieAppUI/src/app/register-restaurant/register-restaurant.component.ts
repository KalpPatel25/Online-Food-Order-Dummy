import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MyrouteService } from '../myServices/myroute.service';
import { RestaurantService } from '../myServices/restaurant.service';
import { UserService } from '../myServices/user.service';
import { RestaurantOwnerComponent } from '../restaurant-owner/restaurant-owner.component';

@Component({
  selector: 'app-register-restaurant',
  templateUrl: './register-restaurant.component.html',
  styleUrls: ['./register-restaurant.component.css']
})
export class RegisterRestaurantComponent implements OnInit {
  type: string = '';
  token: string = '';
  errormessage: string = '';
  username: FormControl;
  password: FormControl;
  email: FormControl;
  restaurantname: FormControl;
  description: FormControl;
  phonenumber: FormControl;
  imageUrl: FormControl;
  restauranttype: FormControl;
  signupFailure: boolean = false;
  public showPassword: boolean=false;

  // imageSource: string="";
  // text = '';

  constructor(private userservice: UserService, private router: MyrouteService, private restService: RestaurantService) {
    this.username = new FormControl('', [
      Validators.required,
      Validators?.minLength(5),
      Validators.pattern(/^(\s+\S+\s*)*(?!\s).*$/),
    ]);
    this.restaurantname = new FormControl('', [
      Validators.required,
      Validators?.minLength(5),
      Validators.pattern(/^(\s+\S+\s*)*(?!\s).*$/),
    ]);
    this.email = new FormControl('', [Validators.required, Validators?.email]);
    this.password = new FormControl('', [
      Validators.required,
      Validators?.maxLength(15),
      Validators?.minLength(5),
    ]);
    this.restauranttype = new FormControl('',[Validators.required]);
    this.description = new FormControl('', [Validators.required]);
    this.phonenumber = new FormControl('', [
      Validators.required,
      Validators?.minLength(1),
      Validators.pattern('[0-9 ]{10}'),
    ]);
    this.imageUrl = new FormControl('', [Validators.required]);
  }

  ngOnInit(): void {}

  onSubmit() {
    // let adddata = {
    //   username: this.username.value,
    //   email: this.email.value,
    //   password: this.password.value,
    //   firstName: this.description.value,
    //   lastName: this.phonenumber.value,
    //   imageUrl: this.imageUrl.value,
    // };
    let userdata ={
      "username":this.username.value,
      "password":this.password.value,
      "type":"RestaurantOwner",
      "email":this.email.value,
      "mobile":this.phonenumber.value
    }

    let restdata = {
      "restaurantName": this.restaurantname.value,
      "restaurantType": this.restauranttype.value,
      "username": this.username.value,
      "restaurantDesc":this.description.value,
      "phoneNumber":this.phonenumber.value,
      "restaurantImageUrl": this.imageUrl.value
    }

    console.log(userdata);
    if(this.username.value && this.email.invalid && this.password.invalid && this.description.invalid && this.phonenumber.invalid && this.imageUrl.invalid)
{
    this.signupFailure=true;
    this.errormessage='Enter all the credentials';
}else{
    this.userservice.registernewUser(userdata).subscribe(
      (result: any) => {
        this.signupFailure = false;
        this.errormessage = 'Registration is successful';
        
      },

      (err: any) => {
        this.signupFailure=true;
      this.errormessage='Enter all the credentials';
      console.log('Enter all the credentials');
        console.log(err);
      }
    );

    this.restService.registerNewRestaurant(restdata).subscribe(
      (result:any)=>{
        this.signupFailure = false;
        this.errormessage = 'Registration is successful';
      },
      (err:any)=>{
        this.signupFailure=true;
      this.errormessage='Enter all the credentials';
      console.log('Enter all the credentials');
        console.log(err);
      }
    )
  }
  this.router.openLogin();
}

public togglePasswordVisibility(): void {
  this.showPassword = !this.showPassword;

 }


  login() {
    this.router.openLogin();
  }
}
