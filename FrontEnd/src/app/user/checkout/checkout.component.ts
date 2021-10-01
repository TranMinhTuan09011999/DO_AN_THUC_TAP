import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OrderDetailRequest } from 'src/app/request/order-detail-request';
import { CartResponse } from 'src/app/response/cart-response';
import { Items } from 'src/app/response/items';
import { CartService } from 'src/app/service/cart.service';
import { CheckoutService } from 'src/app/service/checkout.service';
import { PageService } from 'src/app/service/page.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  page: number = 7;
  totalItems: number = 0;
  public items: Array<Items> = [];
  totalPrice: number = 0;
  dataForm!: FormGroup;
  today= new Date();
  submitted = false;
  token: any;
  public orderDetailRequest: any;
  public orderId: any;

  constructor(private router:Router, private tokenStorageService: TokenStorageService, private pageService: PageService, private cartService: CartService, private fb: FormBuilder, private checkoutService: CheckoutService) { }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.getTotalsItemsInCart();
    this.items = this.cartService.getItems();
    this.total();
    this.infoForm();
  }

  infoForm(){
    const user = this.tokenStorageService.getUser();
    this.dataForm = this.fb.group({
      firstnameOfReveiver: ['', [Validators.required]],
      lastnameOfReveiver: ['', [Validators.required]],  
      addressOfReceiver: ['', [Validators.required]],
      phoneOfReceiver: ['', [Validators.required, Validators.pattern("^[_0-9]{10}")]], 
      customerId: user.id,
    })
  }

  get f() { return this.dataForm.controls; }

  onSubmit() {
    this.submitted = true;
    const val = this.dataForm.value;
    this.checkOut();
  }

  checkOut(){
    this.token = this.tokenStorageService.getToken();
    this.checkoutService.addOrder(this.token, this.dataForm.value)
        .subscribe(
          (data: string) => {
            this.orderId = data;
            console.log(this.orderId);
            let shopping_cart;
            shopping_cart = JSON.parse(localStorage.getItem('cart') || '{}');
            for(let i in shopping_cart){
              console.log("aaaa");
              this.orderDetailRequest = new OrderDetailRequest();
              this.orderDetailRequest.orderId = this.orderId;
              console.log(this.orderDetailRequest.orderId)
              this.orderDetailRequest.productDetailId = shopping_cart[i].productDetail.productDetailId;
              this.orderDetailRequest.price = shopping_cart[i].productDetail.price;
              this.orderDetailRequest.quantity = shopping_cart[i].quantity;
              this.orderDetailRequest.discount = shopping_cart[i].productDetail.percent;
              this.checkoutService.addOrderDetails(this.token, this.orderDetailRequest)
                .subscribe(
                  (data: string) => {
                    this.cartService.clearCart();
                    this.router.navigate(['purchase']).then(this.reloadPage);
                  },
                  error => {
                    console.log(error);
                  });
                  if(shopping_cart[i].productDetail.percent == 0)
                  {
                    this.totalPrice += (shopping_cart[i].productDetail.price*shopping_cart[i].quantity);
                  }else if(shopping_cart[i].productDetail.percent > 0){
                    this.totalPrice += (shopping_cart[i].productDetail.price - shopping_cart[i].productDetail.price*shopping_cart[i].productDetail.percent/100)*shopping_cart[i].quantity;
                  }
            }
          },
          error => {
            console.log(error);
          });
    
 
  }

  getTotalsItemsInCart(){
    this.totalItems = this.cartService.numberOfItems();
  }

  reloadPage(): void {
    window.location.reload();
  }

  total(){
    this.totalPrice = 0;
    let shopping_cart;
    shopping_cart = JSON.parse(localStorage.getItem('cart') || '{}');
    for(let i in shopping_cart){
      if(shopping_cart[i].productDetail.percent == 0)
      {
        this.totalPrice += (shopping_cart[i].productDetail.price*shopping_cart[i].quantity);
      }else if(shopping_cart[i].productDetail.percent > 0){
        this.totalPrice += (shopping_cart[i].productDetail.price - shopping_cart[i].productDetail.price*shopping_cart[i].productDetail.percent/100)*shopping_cart[i].quantity;
      }
    }
  }

}
