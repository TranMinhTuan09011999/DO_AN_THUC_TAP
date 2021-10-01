import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { linear } from '@syncfusion/ej2-angular-charts';
import { Checkout } from 'src/app/checkout';
import { User } from 'src/app/model/user';
import { CountOrder } from 'src/app/response/count-order';
import { StatisticResponse } from 'src/app/response/statistic-response';
import { CustomerService } from 'src/app/service/customer.service';
import { EmployeeServiceService } from 'src/app/service/employee-service.service';
import { ProductService } from 'src/app/service/product.service';
import { PurchaseService } from 'src/app/service/purchase.service';
import { StatisticService } from 'src/app/service/statistic.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  customerCount!: number;
  employeeCount!: number;
  newOrderCount!: number;
  newProductCount!: number;
  token!: string;

  public chartData!: CountOrder[];
  public title!: string;
  public primaryXAxis!: Object;
  public primaryYAxis!: Object;
  public legendSettings!: Object;
  public marker!: Object;
  public tooltip!: Object;

  //================================
  public primaryXAxis1!: Object;
  public primaryYAxis1!: Object;
  public chartData1!: Object[];
  public title1!: string;
  public marker1!: Object;
  public legendSettings1!: Object;
  public tooltip1!: Object;

  constructor(private statisticService: StatisticService, private productService: ProductService, private purchaseService: PurchaseService, private employeeService: EmployeeServiceService, private tokenStorageService: TokenStorageService, private customerService: CustomerService) {
  }

  ngOnInit(): void {

    this.tooltip = {
      enable: true
    }

    this.getCountOrder();
    this.getCountAccount()
  
    this.primaryXAxis = {
      valueType: 'Category'
    };
    this.primaryYAxis = {
      labelFormat: '{value}'
    };
    this.marker = {
      dataLabel:{
          visible: true
      }
    };
    this.legendSettings = {
      visible: true
    };

    this.title = 'Số lượng đơn đặt hàng hàng tháng trong năm';

    //=========================================================

    this.primaryXAxis1 = {
      valueType: 'Category',
    };

    this.marker1 = {
      dataLabel:{
          visible: true
      }
    };
    this.title1 = 'Số lượng khách hàng đăng ký hàng tháng trong năm';

    this.getCustomerCount();
    this.getEmployeeCount();
    this.getNewOrderCount();
    this.getNewProductCount();

    
  }

  getCountOrder(){
    this.token = this.tokenStorageService.getToken();
    this.statisticService.getCountOrderStatistic(this.token)
        .subscribe(
          (data: CountOrder[]) => {
            this.chartData = data;
          },
          error => {
            console.log(error);
          });
  }

  getCountAccount(){
    this.token = this.tokenStorageService.getToken();
    this.statisticService.getCountAccountStatistic(this.token)
        .subscribe(
          (data: CountOrder[]) => {
            this.chartData1 = data;
          },
          error => {
            console.log(error);
          });
  }

  getCustomerCount(){
    this.token = this.tokenStorageService.getToken();
    this.customerService.getCustomerCount(this.token)
        .subscribe(
          (data: any) => {
            this.customerCount = data;
          },
          error => {
            console.log(error);
          });
  }

  getEmployeeCount(){
    this.token = this.tokenStorageService.getToken();
    this.employeeService.getEmployeeCount(this.token)
        .subscribe(
          (data: any) => {
            this.employeeCount = data;
          },
          error => {
            console.log(error);
          });
  }

  getNewOrderCount(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getNewOrderCount(this.token)
        .subscribe(
          (data: any) => {
            this.newOrderCount = data;
          },
          error => {
            console.log(error);
          });
  }

  getNewProductCount(){
    this.token = this.tokenStorageService.getToken();
    this.productService.getNewProductCount(this.token)
        .subscribe(
          (data: any) => {
            this.newProductCount = data;
          },
          error => {
            console.log(error);
          });
  }

}
