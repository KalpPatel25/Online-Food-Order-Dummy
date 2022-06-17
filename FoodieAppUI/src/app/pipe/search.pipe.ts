import { Pipe, PipeTransform } from '@angular/core';
import { Restaurant } from '../model/Restaurant';

@Pipe({
  name: 'search'
})


export class SearchPipe implements PipeTransform {

  transform(restuarants: Restaurant[], searchText: any): any {

    if(searchText==null)
      {return restuarants;}

    return restuarants.filter((restaurant:any)=>
    {

        
      return restaurant.restaurantName.toLowerCase().includes(searchText) || 
        restaurant.restaurantType.toLowerCase().includes(searchText) || restaurant.restaurantDesc.includes(searchText);


    });


  }


}