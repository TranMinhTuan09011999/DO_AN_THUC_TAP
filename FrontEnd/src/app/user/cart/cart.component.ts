import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Items } from 'src/app/response/items';
import { CartService } from 'src/app/service/cart.service';
import { CountService } from 'src/app/service/count.service';
import { ListCartServiceService } from 'src/app/service/list-cart-service.service';
import { PageService } from 'src/app/service/page.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  page: number = 6;
  public items: Array<Items> = [];
  totalPrice: number = 0;
  quantityItem!: any;

  constructor(private ListCartService: ListCartServiceService, private countService: CountService, private router:Router, private pageService: PageService, private cartService: CartService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.items = this.cartService.getItems();
    this.total();
    this.quantityItems();
    this.countService.changeCount(this.quantityItem);
    this.ListCartService.changeCart(this.items);
    this.ListCartService.changePrice(this.totalPrice);
    this.countService.currentCount.subscribe(count => this.quantityItem = count);
    this.ListCartService.currentCart.subscribe(cart => this.items = cart);
    this.ListCartService.currentPrice.subscribe(price => this.totalPrice = price);
  }

  deleteItem(productDetailId: number){
    this.cartService.deleteItem(productDetailId);
    this.items = this.cartService.getItems();
    this.total();
    this.quantityItem = this.cartService.numberOfItems();
    this.countService.changeCount(this.quantityItem);
    this.ListCartService.changeCart(this.items);
    this.ListCartService.changePrice(this.totalPrice);
    console.log("zzzzzzzzzzzzzzzzzzz" + this.quantityItem);
    if(this.quantityItem == 0 || this.quantityItem ==undefined){
      this.router.navigate(['']).then(this.reloadPage);
    }
  }

  addQty(productDetailId: number){
    this.cartService.addQty(productDetailId);
    this.items = this.cartService.getItems();
    this.total();
    this.ListCartService.changeCart(this.items);
    this.ListCartService.changePrice(this.totalPrice);
  }

  subQty(productDetailId: number){
    this.cartService.subQty(productDetailId);
    this.items = this.cartService.getItems();
    this.total();
    this.ListCartService.changeCart(this.items);
    this.ListCartService.changePrice(this.totalPrice);
  }

  total(){
    this.totalPrice = 0;
    let shopping_cart;
    shopping_cart = JSON.parse(localStorage.getItem('cart') || '{}');
    for(let i in shopping_cart){
      if(shopping_cart[i].productDetail.percent == null){
        this.totalPrice += (shopping_cart[i].productDetail.price*shopping_cart[i].quantity);
      }else{
        this.totalPrice += ((shopping_cart[i].productDetail.price - shopping_cart[i].productDetail.price*shopping_cart[i].productDetail.percent/100)*shopping_cart[i].quantity);
      }
    }
  }

  toCheckOut(){
    const user = this.tokenStorageService.getUser();
    if(user.token == null)
    {
      this.router.navigate(['login']).then(this.reloadPage);
    }else{
      this.router.navigate(['checkout']).then(this.reloadPage);
    }
  }

  toHome(){
    this.router.navigate(['']).then(this.reloadPage);
  }

  quantityItems(){
    this.quantityItem = this.cartService.numberOfItems();
  }

  clearCart(){
    this.cartService.clearCart();
    this.router.navigate(['']).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }

}
