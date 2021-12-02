import { Component, OnInit } from '@angular/core';
import { CountOrder } from 'src/app/response/count-order';
import { HotSeliingProductResponse } from 'src/app/response/hot-seliing-product-response';
import { StatisticService } from 'src/app/service/statistic.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-count-customer',
  templateUrl: './count-customer.component.html',
  styleUrls: ['./count-customer.component.css']
})
export class CountCustomerComponent implements OnInit {

  public primaryXAxis1!: Object;
  public primaryYAxis1!: Object;
  public chartData1!: Object[];
  public title1!: string;
  public marker1!: Object;
  public legendSettings1!: Object;
  public tooltip1!: Object;

  public primaryXAxis2!: Object;
  public primaryYAxis2!: Object;
  public chartData2!: Object[];
  public title2!: string;
  public marker2!: Object;
  public legendSettings2!: Object;
  public tooltip2!: Object;

  public primaryXAxis3!: Object;
  public primaryYAxis3!: Object;
  public chartData3!: Object[];
  public title3!: string;
  public marker3!: Object;
  public legendSettings3!: Object;
  public tooltip3!: Object;

  monthFrom = '';
  monthTo = '';
  yearFrom!: number;
  yearTo!: number;
  yearFrom1!: number;
  yearTo1!: number;
  quarter1!: number;
  quarter2!: number;
  token: any;

  constructor(private tokenStorageService: TokenStorageService, private statisticService: StatisticService) { }

  ngOnInit(): void {
  }

  setUp(){
    this.primaryXAxis1 = {
      valueType: 'Category',
    };

    this.marker1 = {
      dataLabel:{
          visible: true
      }
    };
    this.title1 = 'Số lượng khách hàng đăng ký theo tháng';
  }

  setUp2(){
    this.primaryXAxis2 = {
      valueType: 'Category',
    };

    this.marker2 = {
      dataLabel:{
          visible: true
      }
    };
    this.title2 = 'Số lượng khách hàng đăng ký theo tháng';
  }

  setUp3(){
    this.primaryXAxis3 = {
      valueType: 'Category',
    };

    this.marker3 = {
      dataLabel:{
          visible: true
      }
    };
    this.title3 = 'Số lượng khách hàng đăng ký theo tháng';
  }

  search(){
    console.log(this.monthFrom);
    console.log(this.monthTo);
    this.token = this.tokenStorageService.getToken();
    // if(this.monthFrom != '' &&  this.monthTo == ''){
    //   this.statisticService.getStatisticHotSelling(this.token, this.monthFrom)
    //   .subscribe(
    //     (data: HotSeliingProductResponse[]) => {
    //       this.hotSellingList = data;
    //       this.setUp();
    //     },
    //     error => {
    //       console.log(error);
    //     });
    //   }
      if(this.monthFrom != '' &&  this.monthTo != ''){
        this.statisticService.getStatisticCountCustomerFromTo(this.token, this.monthFrom, this.monthTo)
      .subscribe(
        (data: CountOrder[]) => {
          this.chartData1 = data;
          this.setUp();
        },
        error => {
          console.log(error);
        });
      }
    }

    searchQuater(){
      console.log(this.yearFrom);
      console.log(this.yearTo);
      console.log(this.quarter1);
      console.log(this.quarter2);
      this.token = this.tokenStorageService.getToken();
      // if(this.quarter1 != undefined && this.yearFrom != undefined && this.quarter2 == undefined && this.yearTo == undefined){
      //   this.statisticService.getStatisticHotSellingByQuater(this.token, this.quarter1, this.yearFrom)
      // .subscribe(
      //   (data: HotSeliingProductResponse[]) => {
      //     this.hotSellingList1 = data;
      //     console.log(this.hotSellingList1);
      //     this.setUp1();
      //   },
      //   error => {
      //     console.log(error);
      //   });
      // }else{
      //   this.statisticService.getStatisticHotSellingByQuaterFromTo(this.token, this.quarter1, this.yearFrom, this.quarter2, this.yearTo)
      // .subscribe(
      //   (data: HotSeliingProductResponse[]) => {
      //     this.hotSellingList1 = data;
      //     console.log(this.hotSellingList1);
      //     this.setUp1();
      //   },
      //   error => {
      //     console.log(error);
      //   });
      // }
      if(this.quarter1 != undefined && this.yearFrom != undefined && this.quarter2 != undefined && this.yearTo != undefined){
          this.statisticService.getStatisticCountCustomerQuaterFromTo(this.token, this.quarter1, this.yearFrom, this.quarter2, this.yearTo)
        .subscribe(
          (data: CountOrder[]) => {
            console.log(data);
            this.chartData2 = data;
            this.setUp2();
          },
          error => {
            console.log(error);
          });
      }
    }

    searchYear(){
      // this.token = this.tokenStorageService.getToken();
      // if(this.yearFrom1 != undefined && this.yearTo1 == undefined){
      //   this.statisticService.getStatisticHotSellingByYear(this.token, this.yearFrom1)
      // .subscribe(
      //   (data: HotSeliingProductResponse[]) => {
      //     this.hotSellingList2 = data;
      //     this.setUp2();
      //   },
      //   error => {
      //     console.log(error);
      //   });
      // }
      // if(this.yearFrom1 != undefined && this.yearTo1 != undefined){
      //   this.statisticService.getStatisticHotSellingByYearFromTo(this.token, this.yearFrom1, this.yearTo1)
      // .subscribe(
      //   (data: HotSeliingProductResponse[]) => {
      //     this.hotSellingList2 = data;
      //     this.setUp2();
      //   },
      //   error => {
      //     console.log(error);
      //   });
      // }
      
      this.token = this.tokenStorageService.getToken();
      if(this.yearFrom1 != undefined && this.yearTo1 != undefined){
        console.log(this.yearFrom1);
      console.log(this.yearTo1);
        this.statisticService.getStatisticCountCustomerByYearFromTo(this.token, this.yearFrom1, this.yearTo1)
      .subscribe(
        (data: CountOrder[]) => {
          this.chartData3 = data;
          this.setUp3();
        },
        error => {
          console.log(error);
        });
      }
    }
}
