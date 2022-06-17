import { Time } from "@angular/common";
import { CartItem } from "./CartItem";


export class Order{
    orderId?:string;
    orderStatus?:string;
    restaurantId?:string;
    orderTime?:Time;
    deliveryTime?:Time;
    itemList?:CartItem[];
    totalPrice?:number;
    tax:number=0;
    amount:number=0;
    username:string="";
    restaurantName:string="";
}