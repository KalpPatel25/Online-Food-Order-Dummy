import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpcli: HttpClient) { }

  GenerateTokenfromServer(mydata:any) : Observable<any>
{
  console.log(mydata);
  return this.httpcli.post('http://localhost:9091/users/api/login', mydata);
}

storeToken(tok:any)
{
sessionStorage.setItem("mytoken",tok);
}

getToken() : any
{
 return sessionStorage.getItem("mytoken");
}

isUserAuthenticated(token:any): Promise<boolean>
{
 return this.httpcli.post(`http://localhost:9091/users/api/isAuthenticated` , {}, 
 {headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)})
.pipe
 (map(
   (res:any) => { return(res['isAuthenticated']);})).toPromise()
 
}

registernewUser(adddata : any)
{
  
  return this.httpcli.post(`http://localhost:9091/users/api/register`,adddata);

}


getUserType(username:string):Observable<string>{
  return this.httpcli.get<string>(`http://localhost:9091/users/api/getUsertype/${username}`)
}


}
