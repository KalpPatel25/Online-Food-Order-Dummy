import { Item } from "./Item";

export class Restaurant{
    restaurantId:string="";
    itemList?:Array<Item>;
    status?:boolean;
    username:string="";
    restaurantType:string="";
    restaurantDesc:string="";
    restaurantName:string="";
    restaurantAddress:string="";
    phoneNumber:string="";
    restaurantImageUrl:string="";
    addToFav?:boolean;
}
