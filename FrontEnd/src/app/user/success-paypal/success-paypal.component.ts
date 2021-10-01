import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from 'src/app/model/order';
import { PageService } from 'src/app/service/page.service';
import { PurchaseService } from 'src/app/service/purchase.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-success-paypal',
  templateUrl: './success-paypal.component.html',
  styleUrls: ['./success-paypal.component.css']
})
export class SuccessPaypalComponent implements OnInit {

  page: number = 10;
  orderId!: string;
  token!: string;
  order!: Order;

  constructor(private router: Router, private purchaseService: PurchaseService, private tokenStorageService: TokenStorageService, private pageService: PageService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.route.queryParams.subscribe(params => {
      this.orderId = params['orderId'];
     });
     console.log(this.orderId);
     this.payment();
  }

  payment(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.payment(this.token, this.orderId, 1)
    .subscribe(
      (data: Order) => {
        this.order = data;
      },
      error => {
        console.log(error);
      });
  }

  toHome(){
    this.router.navigate(['../']).then(this.reloadPage);
  }

  toPurchase(){
    this.router.navigate(['../purchase']).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }

}
