import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/model/order';
import { OrderDetail } from 'src/app/model/order-detail';
import { OrderResponse } from 'src/app/response/order-response';
import { CheckoutService } from 'src/app/service/checkout.service';
import { PageService } from 'src/app/service/page.service';
import { PurchaseService } from 'src/app/service/purchase.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-purchase-order',
  templateUrl: './purchase-order.component.html',
  styleUrls: ['./purchase-order.component.css']
})
export class PurchaseOrderComponent implements OnInit {

  page: number = 6;
  orders: Array<Order> = [];
  orderDetails: Array<OrderDetail> = [];
  token: any;
  total: number = 0;
  unconfirmOrders: Array<Order> = [];
  confirmOrders: Array<Order> = [];
  canceledOrders: Array<Order> = [];
  deliveringOrders: Array<OrderResponse> = [];
  deliveredOrders: Array<OrderResponse> = [];
  linkPaypal!: string;

  config: any;
  config1: any;
  config2: any;
  config3: any;
  config4: any;

  constructor(private checkoutService: CheckoutService, private pageService: PageService, private purchaseService: PurchaseService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.getAllUnconfirmOrderByCustomer();
    this.getAllConfirmOrderByCustomer();
    this.getAllDeliveringOrderByCustomer();
    this.getAllDeliveredOrderByCustomer();
    this.getAllCanceledOrderByCustomer();
  }

  getAllOrder(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.purchaseService.getOrder(this.token, user.id)
        .subscribe(
          (data: Order[]) => {
            this.orders = data;
          },
          error => {
            console.log(error);
          });
  }

  showOrderDetail(orderId: string){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getAllOrderDetailByOrderId(this.token, orderId)
    .subscribe(
      (data: OrderDetail[]) => {
        this.orderDetails = data;
        this.getTotal();
      },
      error => {
        console.log(error);
      });
  }

  getTotal(){
    this.total = 0;
    for(let orderDetail of this.orderDetails){
      if(orderDetail.discount == 0){
        this.total += orderDetail.price*orderDetail.quantity
      }else{
        this.total += (orderDetail.price - orderDetail.price*orderDetail.discount/100)*orderDetail.quantity
      }
    }
  }

  getAllUnconfirmOrderByCustomer(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.purchaseService.getUnconfirmOrderByCustomer(this.token, user.id, 1)
        .subscribe(
          (data: Order[]) => {
            this.unconfirmOrders = data;
          },
          error => {
            console.log(error);
          });
    
          this.config = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.unconfirmOrders.values.length
        };
  }

  pageChanged(event: any){
    this.config.currentPage = event;
  }

  pageChanged1(event: any){
    this.config1.currentPage = event;
  }

  pageChanged2(event: any){
    this.config2.currentPage = event;
  }

  pageChanged3(event: any){
    this.config3.currentPage = event;
  }

  pageChanged4(event: any){
    this.config4.currentPage = event;
  }

  getAllCanceledOrderByCustomer(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.purchaseService.getUnconfirmOrderByCustomer(this.token, user.id, 5)
        .subscribe(
          (data: Order[]) => {
            this.canceledOrders = data;
          },
          error => {
            console.log(error);
          });

          this.config4 = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.canceledOrders.values.length
        };
  }

  getAllConfirmOrderByCustomer(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.purchaseService.getUnconfirmOrderByCustomer(this.token, user.id, 2)
        .subscribe(
          (data: Order[]) => {
            this.confirmOrders = data;
          },
          error => {
            console.log(error);
          });

          this.config1 = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.confirmOrders.values.length
        };
  }

  getAllDeliveringOrderByCustomer(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.purchaseService.getDeliveringOrderByCustomer(this.token, user.id, 3)
        .subscribe(
          (data: OrderResponse[]) => {
            this.deliveringOrders = data;
          },
          error => {
            console.log(error);
          });

          this.config2 = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.deliveringOrders.values.length
        };
  }

  getAllDeliveredOrderByCustomer(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.purchaseService.getDeliveringOrderByCustomer(this.token, user.id, 4)
        .subscribe(
          (data: OrderResponse[]) => {
            this.deliveredOrders = data;
          },
          error => {
            console.log(error);
          });

          this.config3 = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.deliveredOrders.values.length
        };
  }

  cancel(orderId: string){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.cancel(this.token, orderId, 5)
    .subscribe(
      (data: Order) => {
        this.getAllUnconfirmOrderByCustomer();
        this.getAllConfirmOrderByCustomer();
        this.getAllDeliveringOrderByCustomer();
        this.getAllDeliveredOrderByCustomer();
        this.getAllCanceledOrderByCustomer();
      },
      error => {
        console.log(error);
      });
  }

  payment(orderId: string){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getAllOrderDetailByOrderId(this.token, orderId)
    .subscribe(
      (data: OrderDetail[]) => {
        this.orderDetails = data;
        this.getTotal();
        this.checkoutService.paymentPaypal(orderId, this.total)
          .subscribe(
            (data) => {
              this.linkPaypal = data;
              window.location.href = this.linkPaypal;
            },
            error => {
              console.log(error);
            });
      },
      error => {
        console.log(error);
      });
  }

}
