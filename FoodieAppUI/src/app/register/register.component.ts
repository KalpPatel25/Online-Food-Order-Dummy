import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MyrouteService } from '../myServices/myroute.service';
import { UserService } from '../myServices/user.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  type: string = '';
  token: string = '';
  errormessage: string = '';
  username: FormControl;
  password: FormControl;
  email: FormControl;
  firstName: FormControl;
  lastName: FormControl;
  city: FormControl;
  country: FormControl;
  mobile: FormControl;
signupFailure: boolean = false;
public showPassword: boolean=false;

  constructor(private userservice: UserService, private router: MyrouteService) {
    this.username = new FormControl('', [
      Validators.required,
      Validators?.minLength(5),
      Validators.pattern('^[A-Za-z]+$'),
    ]);
    this.email = new FormControl('', [Validators.required, Validators?.email]);
    this.password = new FormControl('', [
      Validators.required,
      Validators?.maxLength(15),
      Validators?.minLength(5),
      
    ]);
    this.firstName = new FormControl('', [
      Validators.required,
      Validators?.minLength(3),
      
    ]);
    this.lastName = new FormControl('', [
      Validators.required,
      Validators?.minLength(3),
      
    ]);

    this.city = new FormControl('', [
      Validators.required,
      Validators?.minLength(4),
      
    ]);
    this.country = new FormControl('', [
      Validators.required,
      Validators?.minLength(4),
      
    ]);
    this.mobile = new FormControl('', [
      Validators.required,
      Validators?.minLength(1),
      Validators.pattern('[0-9 ]{10}'),
    ]);
  }

  ngOnInit() {}

  onSubmit() {
    let adddata = {
      username: this.username.value,
      email: this.email.value,
      password: this.password.value,
      firstName: this.firstName.value,
      lastName: this.lastName.value,
      city: this.city.value,
      country: this.country.value,
      mobile: this.mobile.value,
      type: "User"

      // "Type":this.type.value
    };

    console.log(adddata);
    console.log(adddata.username.includes(' '));
    if(this.username.invalid || this.email.invalid || this.password.invalid || this.firstName.invalid || this.lastName.invalid || this.city.invalid || this.country.invalid || this.mobile.invalid)
    {
      this.signupFailure=true;
      this.errormessage='Enter all the credentials';
     
    }else{

    this.userservice.registernewUser(adddata).subscribe(
      (result: any) => {
        this.signupFailure = false;
        this.errormessage = 'Registration is successful';
        console.log(this.errormessage);
        this.router.openLogin();
      },

      (err: any) => {
        this.signupFailure=true;
      this.errormessage='Username already exists';
              console.log('Enter all the credentials');
        console.log(err);
      }
    );
  }
  }

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  
   }

  login() {
    this.router.openLogin();
  }
}
