import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Items } from 'src/app/response/items';
import { CartService } from 'src/app/service/cart.service';
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

  constructor(private router:Router, private pageService: PageService, private cartService: CartService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.items = this.cartService.getItems();
    this.total();
  }

  deleteItem(productDetailId: number){
    this.cartService.deleteItem(productDetailId);
    this.items = this.cartService.getItems();
    this.total();
  }

  addQty(productDetailId: number){
    this.cartService.addQty(productDetailId);
    this.items = this.cartService.getItems();
    this.total();
  }

  subQty(productDetailId: number){
    this.cartService.subQty(productDetailId);
    this.items = this.cartService.getItems();
    this.total();
  }

  total(){
    this.totalPrice = 0;
    let shopping_cart;
    shopping_cart = JSON.parse(localStorage.getItem('cart') || '{}');
    for(let i in shopping_cart){
      this.totalPrice += (shopping_cart[i].productDetail.price*shopping_cart[i].quantity);
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

  reloadPage(): void {
    window.location.reload();
  }

}
