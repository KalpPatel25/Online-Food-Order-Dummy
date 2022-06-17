import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { MyrouteService } from './myServices/myroute.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuardGuard implements CanActivate {
  constructor(private rouobj: MyrouteService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      let token = sessionStorage.getItem('mytoken');
      let type = sessionStorage.getItem('userType');

      if(token==null || type!=="User")
      {
        this.rouobj.openLogin();
        console.log("1st");
        return false;
      }
      else{
        return true;
      }
  }
  
}
