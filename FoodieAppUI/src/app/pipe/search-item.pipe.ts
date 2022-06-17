import { Pipe, PipeTransform } from '@angular/core';
import { Item } from '../model/Item';

@Pipe({
  name: 'searchItem'
})
export class SearchItemPipe implements PipeTransform {

  
  transform(items: Item[], searchText: any): any {

    if(searchText==null)
      {return items;}

    return items.filter((item:any)=>
    {

        
      return item.itemName.toLowerCase().includes(searchText) || 
        item.category.toLowerCase().includes(searchText);


    });


  }

}
