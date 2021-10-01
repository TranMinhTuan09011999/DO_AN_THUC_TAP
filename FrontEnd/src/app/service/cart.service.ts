import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { ProductDetail } from '../model/product-detail';
import { CartResponse } from '../response/cart-response';
import { Items } from '../response/items';

let itemsInCart = [];
let cart = [];

@Injectable({
  providedIn: 'root'
})
export class CartService {

  items!: Items | null;


  constructor() { }

  add(productDetail: CartResponse, quantity: number){
    let local_storage;
    let itemsInCart = []
    this.items = {
      productDetail: productDetail,
      quantity: quantity,
    }
    if(localStorage.getItem('cart')  == null){
      local_storage =[];
      console.log("LOCALSTORAGE NULL",JSON.parse(localStorage.getItem('cart')  || '{}'));
      itemsInCart.push(this.items);
      localStorage.setItem('cart', JSON.stringify(itemsInCart));
      console.log('Pushed first Item: ', itemsInCart);
    }else
    {
      local_storage = JSON.parse(localStorage.getItem('cart') || '{}');
      console.log("LOCAL STORAGE HAS ITEMS",JSON.parse(localStorage.getItem('cart') || '{}'));
      for(var i in local_storage)
      {
        if(this.items.productDetail.productDetailId == local_storage[i].productDetail.productDetailId)
        {
          local_storage[i].quantity += quantity;
          console.log("Quantity for "+i+" : "+ local_storage[i].quantity);
          console.log('same product! index is ', i); 
          this.items = null;
          break;  
        }
      }
      if(this.items){
        itemsInCart.push(this.items);
      }
      local_storage.forEach(function (item: any){
        itemsInCart.push(item);
      })
      localStorage.setItem('cart', JSON.stringify(itemsInCart));
    }
  }

  addQty(productDetailId: number)
  {
    let shopping_cart;
    shopping_cart = JSON.parse(localStorage.getItem('cart') || '{}' );
    for(let i in shopping_cart){
      if(productDetailId == shopping_cart[i].productDetail.productDetailId){
        shopping_cart[i].quantity +=1;
        break;
      }
    }
    localStorage.setItem('cart', JSON.stringify(shopping_cart));
  }

  subQty(productDetailId: number)
  {
    let shopping_cart;
    shopping_cart = JSON.parse(localStorage.getItem('cart') || '{}' );
    for(let i in shopping_cart){
      if(productDetailId == shopping_cart[i].productDetail.productDetailId){
        shopping_cart[i].quantity -=1;
        break;
      }
    }
    localStorage.setItem('cart', JSON.stringify(shopping_cart));
  }

  getItems(){
    console.log("Cart: ", JSON.parse(localStorage.getItem('cart') || '{}'));
    return this.items = JSON.parse(localStorage.getItem('cart') || '{}');
   }

   numberOfItems(){
    let itemsInCart = JSON.parse(localStorage.getItem('cart') || '{}');
    return itemsInCart.length;
  }

  deleteItem(productDetailId: number){
    let shopping_cart;
    let index;
    shopping_cart = JSON.parse(localStorage.getItem('cart') || '{}');
    for(let i in shopping_cart){
      if (productDetailId == shopping_cart[i].productDetail.productDetailId)
      {
        index = i;
        console.log(index);
      }
    }
    shopping_cart.splice(index, 1);
    console.log("shopping_cart ", shopping_cart);
    localStorage.setItem('cart', JSON.stringify(shopping_cart));
  }

  clearCart(){
    localStorage.removeItem('cart');
  }
}
