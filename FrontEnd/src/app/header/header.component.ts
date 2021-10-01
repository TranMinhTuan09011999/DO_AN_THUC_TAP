import { AuthService } from './../service/auth.service';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../service/token-storage.service';
import { CountService } from '../service/count.service';
import { Router } from '@angular/router';
import { Items } from '../response/items';
import { CartService } from '../service/cart.service';
import { TranslateService } from '@ngx-translate/core';
import { ListCartServiceService } from '../service/list-cart-service.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  username!: string;
  public items: Array<Items> = [];
  token = '';
  totalPrice: number = 0;

  //count!: number;
  count!: number;
  nameUser: string = "";
  message!: string;
  closeResult!: string;

  constructor(private modalService: NgbModal, private ListCartService:ListCartServiceService, private translate: TranslateService, private cartService: CartService, private router: Router, private tokenStorageService: TokenStorageService, private countService: CountService) {
    translate.setDefaultLang('vn');
  }

  ngOnInit(): void {
    this.items = this.cartService.getItems();
    this.total();
    this.countCart();
    this.countService.changeCount(this.count);
    this.ListCartService.changeCart(this.items);
    this.ListCartService.changePrice(this.totalPrice);
    this.countService.currentCount.subscribe(count => this.count = count);
    this.ListCartService.currentCart.subscribe(cart => this.items = cart);
    this.ListCartService.currentPrice.subscribe(price => this.totalPrice = price);
    // if(this.count == undefined){
    //   this.countService.currentCount.subscribe(count => this.count = count);
    //   this.ListCartService.currentCart.subscribe(cart => this.items = cart);
    //   this.ListCartService.currentPrice.subscribe(price => this.totalPrice = price);
    // }else{
    //   this.countService.changeCount(this.count);
    //   this.ListCartService.changeCart(this.items);
    //   this.ListCartService.changePrice(this.totalPrice);
    //   this.countService.currentCount.subscribe(count => this.count = count);
    //   this.ListCartService.currentCart.subscribe(cart => this.items = cart);
    //   this.ListCartService.currentPrice.subscribe(price => this.totalPrice = price);
    // }
    const user = this.tokenStorageService.getUser();
    if(user.token != null)
    {
      this.nameUser = user.lastname + " " + user.firstname;
    }
  }

  countCart(){
    this.count = this.cartService.numberOfItems();
    if(this.count == undefined){
      this.count = 0;
    }
  }

  useLanguage(language: string): void {
    this.translate.use(language);
}

  toCheckOut(content: any){
    const user = this.tokenStorageService.getUser();
    if(user.token == null)
    {
      this.router.navigate(['login']).then(this.reloadPage);
    }else{
      if(this.count == 0 || this.count == undefined)
      {
        this.message = 'Cart is empty...';
          this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
            console.log(this.closeResult);
          }, (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
            console.log(this.closeResult);
          });
          return;
        }
    }
    this.router.navigate(['checkout']).then(this.reloadPage);
  }

  deleteItem(productDetailId: number){
    this.cartService.deleteItem(productDetailId);
    this.items = this.cartService.getItems();
    this.total();
    this.countCart();
    this.countService.changeCount(this.count);
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

  isLoggedIn():boolean{
    this.token = this.tokenStorageService.getToken();
    if(this.token == '{}')
    {
      return false;
    }else{    
      const user = this.tokenStorageService.getUser();
      this.username = user.username;
      return true;
    }
}



  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigate(['../login']).then(this.reloadPage);
  }

  directPage(){
    this.router.navigate(['../']).then(this.reloadPage);
  }

  toViewCart(content: any){
    const user = this.tokenStorageService.getUser();
    if(user.token == null)
    {
      this.router.navigate(['login']).then(this.reloadPage);
    }else{
      if(this.count == 0 || this.count == undefined)
      {
        this.message = 'Cart is empty...';
          this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
            console.log(this.closeResult);
          }, (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
            console.log(this.closeResult);
          });
          return;
        }
    }
    this.router.navigate(['../cart']).then(this.reloadPage);
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  directRegisterPage(){
    this.router.navigate(['../register']).then(this.reloadPage);
  }

  directLoginPage(){
    this.router.navigate(['../login']).then(this.reloadPage);
  }

  directMyAccount(){
    this.router.navigate(['../myAccount']).then(this.reloadPage);
  }

  directPurchase(){
    this.router.navigate(['../purchase']).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }
  
}
