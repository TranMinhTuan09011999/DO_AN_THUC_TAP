import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/model/order';
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
  token: any;

  constructor(private pageService: PageService, private purchaseService: PurchaseService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.getAllOrder();
  }

  getAllOrder(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getOrder(this.token)
        .subscribe(
          (data: Order[]) => {
            this.orders = data;
          },
          error => {
            console.log(error);
          });
  }

}
