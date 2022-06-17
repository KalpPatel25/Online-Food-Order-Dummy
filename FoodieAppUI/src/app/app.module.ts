import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {FlexLayoutModule} from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { LoginComponent } from './login/login.component';
import { RestaurantOwnerComponent } from './restaurant-owner/restaurant-owner.component';
import { RestaurantAdminComponent } from './restaurant-admin/restaurant-admin.component';
import { RegisterRestaurantComponent } from './register-restaurant/register-restaurant.component';
import { CartComponent } from './cart/cart.component';
import { OrderUserComponent } from './order-user/order-user.component';
import { OrderOwnerComponent } from './order-owner/order-owner.component';
import { OrderAdminComponent } from './order-admin/order-admin.component';
import { RestaurantUserComponent } from './restaurant-user/restaurant-user.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule,FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';
import { Routes,Router, RouterModule, UrlSegment } from '@angular/router';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSelectModule} from '@angular/material/select';
import { ResSearchComponent } from './res-search/res-search.component';
import {MatMenuModule} from '@angular/material/menu';
import { SearchPipe } from './pipe/search.pipe';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { InfoPageComponent } from './info-page/info-page.component';
import { UserGuardGuard } from './user-guard.guard';
import { AdminGuardGuard } from './admin-guard.guard';
import { OwnerGuardGuard } from './owner-guard.guard';
import { AboutUsComponent } from './about-us/about-us.component';
import { DialogComponent } from './dialog/dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTabsModule} from '@angular/material/tabs';
import { OrderCompleteComponent } from './order-complete/order-complete.component';
import { SearchItemPipe } from './pipe/search-item.pipe';
const routes : Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'about-us',
    component: AboutUsComponent
  },
  {
    path: 'info-page',
    component: InfoPageComponent
  },
  {
    path: 'order-complete',
    component: OrderCompleteComponent,
    canActivate: [UserGuardGuard]
  },
  {
    path: 'cart',
    component: CartComponent,
    canActivate: [UserGuardGuard]
  } ,
  {
    path: 'favourite',
    component: FavouriteComponent,
    canActivate: [UserGuardGuard]
  },
  {
    path: 'footer',
    component: FooterComponent
  },
  {
    path:'header',
    component: HeaderComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path:'order-admin',
    component:OrderAdminComponent,
    canActivate: [AdminGuardGuard]
  },  
  {
    path:'order-owner',
    component: OrderOwnerComponent,
    canActivate:[OwnerGuardGuard]
  },
  {
    path:'order-user',
    component: OrderUserComponent,
    canActivate: [UserGuardGuard]
  },
  {
    path:'register',
    component: RegisterComponent
  },
  {
    path:'restaurant-admin',
    component: RestaurantAdminComponent,
    canActivate: [AdminGuardGuard]
  },
  {
    path:'restaurant-owner',
    component: RestaurantOwnerComponent,
    canActivate: [OwnerGuardGuard]
  },
  {
    path:'restaurant-user',
    component: RestaurantUserComponent,
    canActivate: [UserGuardGuard]
  },
  {
    path:'register-restaurant',
    component: RegisterRestaurantComponent,
  },
  {
    path:'res-search',  
    component:ResSearchComponent
  },
  {
    path: 'dialog',
    component: DialogComponent,
    canActivate: [UserGuardGuard]
  },
  {
    path: '',
    redirectTo:'info-page',
    pathMatch: 'full'
  }
  

]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    RegisterComponent,
    HomeComponent,
    FavouriteComponent,
    
    LoginComponent,
    RestaurantOwnerComponent,
    RestaurantAdminComponent,
    RegisterRestaurantComponent,
    CartComponent,
    OrderUserComponent,
    OrderOwnerComponent,
    OrderAdminComponent,
    SearchPipe,
    RestaurantUserComponent,
    ResSearchComponent,
    InfoPageComponent,
    AboutUsComponent,
    DialogComponent,
    OrderCompleteComponent,
    SearchItemPipe
  ],
  imports: [
    BrowserModule,
    FlexLayoutModule,
    MatButtonModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatIconModule,
    MatToolbarModule,
    MatExpansionModule,
    MatSlideToggleModule,
    MatFormFieldModule,
    HttpClientModule,
    MatCardModule,
    MatSnackBarModule,
    MatSidenavModule,
    MatMenuModule,
    MatSelectModule,
    MatInputModule,ReactiveFormsModule,FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
