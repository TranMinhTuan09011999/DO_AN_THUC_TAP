import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HotSeliingProductResponse } from 'src/app/response/hot-seliing-product-response';
import { StatisticService } from 'src/app/service/statistic.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-hot-selling-products',
  templateUrl: './hot-selling-products.component.html',
  styleUrls: ['./hot-selling-products.component.css']
})
export class HotSellingProductsComponent implements OnInit {

  public hotSellingList!: Object[];
  public chartTitle!: string;
  public chartLabel!: Object;
  public legend!: Object;
  public tooltipSettings!: Object;
  public center!: Object ;
  public enableAnimation!: boolean ;
  public startAngle!: number;
  public endAngle!: number;
  public explode!: boolean ;

  public hotSellingList1!: Object[];
  public chartTitle1!: string;
  public chartLabel1!: Object;
  public legend1!: Object;
  public tooltipSettings1!: Object;
  public center1!: Object ;
  public enableAnimation1!: boolean ;
  public startAngle1!: number;
  public endAngle1!: number;
  public explode1!: boolean ;

  public hotSellingList2!: Object[];
  public chartTitle2!: string;
  public chartLabel2!: Object;
  public legend2!: Object;
  public tooltipSettings2!: Object;
  public center2!: Object ;
  public enableAnimation2!: boolean ;
  public startAngle2!: number;
  public endAngle2!: number;
  public explode2!: boolean ;

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
    this.chartTitle = "Top Sản Phẩm bán chạy";
    this.tooltipSettings = {
      enable: true,
      format: '${point.x} : <b>${point.y}</b>' 
    }

    this.chartLabel = {
      visible: true,
      position: "Inside",
      name: 'text'
    };

    this.legend = {
      visible: true
    }

    this.center = {x: '50%', y: '50%'};

    this.enableAnimation = false;

    this.startAngle = 0;
    this.endAngle = 360;
    this.explode = true;
  }

  setUp1(){
    this.chartTitle1 = "Top Sản Phẩm bán chạy theo quý";
    this.tooltipSettings1 = {
      enable: true,
      format: '${point.x} : <b>${point.y}</b>' 
    }

    this.chartLabel1 = {
      visible: true,
      position: "Inside",
      name: 'text'
    };

    this.legend1 = {
      visible: true
    }

    this.center1 = {x: '50%', y: '50%'};

    this.enableAnimation1 = false;

    this.startAngle1 = 0;
    this.endAngle1 = 360;
    this.explode1 = true;
  }

  setUp2(){
    this.chartTitle2 = "Top Sản Phẩm bán chạy theo năm";
    this.tooltipSettings2 = {
      enable: true,
      format: '${point.x} : <b>${point.y}</b>' 
    }

    this.chartLabel2 = {
      visible: true,
      position: "Inside",
      name: 'text'
    };

    this.legend2 = {
      visible: true
    }

    this.center2 = {x: '50%', y: '50%'};

    this.enableAnimation2 = false;

    this.startAngle2 = 0;
    this.endAngle2 = 360;
    this.explode2 = true;
  }

  search(){
    console.log(this.monthFrom);
    console.log(this.monthTo);
    this.token = this.tokenStorageService.getToken();
    if(this.monthFrom != '' &&  this.monthTo == ''){
      this.statisticService.getStatisticHotSelling(this.token, this.monthFrom)
      .subscribe(
        (data: HotSeliingProductResponse[]) => {
          this.hotSellingList = data;
          this.setUp();
        },
        error => {
          console.log(error);
        });
      }
      if(this.monthFrom != '' &&  this.monthTo != ''){
        this.statisticService.getStatisticHotSellingFromTo(this.token, this.monthFrom, this.monthTo)
      .subscribe(
        (data: HotSeliingProductResponse[]) => {
          this.hotSellingList = data;
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
      if(this.quarter1 != undefined && this.yearFrom != undefined && this.quarter2 == undefined && this.yearTo == undefined){
        this.statisticService.getStatisticHotSellingByQuater(this.token, this.quarter1, this.yearFrom)
      .subscribe(
        (data: HotSeliingProductResponse[]) => {
          this.hotSellingList1 = data;
          console.log(this.hotSellingList1);
          this.setUp1();
        },
        error => {
          console.log(error);
        });
      }else{
        this.statisticService.getStatisticHotSellingByQuaterFromTo(this.token, this.quarter1, this.yearFrom, this.quarter2, this.yearTo)
      .subscribe(
        (data: HotSeliingProductResponse[]) => {
          this.hotSellingList1 = data;
          console.log(this.hotSellingList1);
          this.setUp1();
        },
        error => {
          console.log(error);
        });
      }
    }

    searchYear(){
      this.token = this.tokenStorageService.getToken();
      if(this.yearFrom1 != undefined && this.yearTo1 == undefined){
        this.statisticService.getStatisticHotSellingByYear(this.token, this.yearFrom1)
      .subscribe(
        (data: HotSeliingProductResponse[]) => {
          this.hotSellingList2 = data;
          this.setUp2();
        },
        error => {
          console.log(error);
        });
      }
      if(this.yearFrom1 != undefined && this.yearTo1 != undefined){
        this.statisticService.getStatisticHotSellingByYearFromTo(this.token, this.yearFrom1, this.yearTo1)
      .subscribe(
        (data: HotSeliingProductResponse[]) => {
          this.hotSellingList2 = data;
          this.setUp2();
        },
        error => {
          console.log(error);
        });
      }
    }
  }
