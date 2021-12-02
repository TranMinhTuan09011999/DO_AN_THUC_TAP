import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Checkout } from 'src/app/checkout';
import { User } from 'src/app/model/user';
import { StatisticResponse } from 'src/app/response/statistic-response';
import { StatisticService } from 'src/app/service/statistic.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-revenue',
  templateUrl: './revenue.component.html',
  styleUrls: ['./revenue.component.css']
})
export class RevenueComponent implements OnInit {

  public data!: Object[];
  public chartTitle!: string;
  public chartLabel!: Object;
  public legend!: Object;
  public tooltipSettings!: Object;
  public center!: Object ;
  public enableAnimation!: boolean ;
  public startAngle!: number;
  public endAngle!: number;
  public explode!: boolean ;

  public data1!: Object[];
  public chartTitle1!: string;
  public chartLabel1!: Object;
  public legend1!: Object;
  public tooltipSettings1!: Object;
  public center1!: Object ;
  public enableAnimation1!: boolean ;
  public startAngle1!: number;
  public endAngle1!: number;
  public explode1!: boolean ;

  public data2!: Object[];
  public chartTitle2!: string;
  public chartLabel2!: Object;
  public legend2!: Object;
  public tooltipSettings2!: Object;
  public center2!: Object ;
  public enableAnimation2!: boolean ;
  public startAngle2!: number;
  public endAngle2!: number;
  public explode2!: boolean ;

  dataForm!: FormGroup;
  submitted = false;
  toDate = '';
  fromDate = '';
  token: any;
  jstoday = '';
  jstoday1 = '';
  disabled = true;
  message!: string;
  closeResult!: string;

  yearFrom!: number;
  yearTo!: number;
  yearFrom1!: number;
  yearTo1!: number;
  quarter1!: number;
  quarter2!: number;

  constructor(private modalService: NgbModal, private fb: FormBuilder,  private tokenStorageService: TokenStorageService, private statisticService: StatisticService) {
    
    let today= new Date();
    this.jstoday = formatDate(today, 'yyyy-MM', 'en-VN');
    console.log(this.jstoday);
    
    let today1 = new Date();
    today1.setTime(today1.getTime() - 150*24*60*60*1000);
    this.jstoday1 = formatDate(today1, 'yyyy-MM', 'en-VN');
    console.log(this.jstoday1);
    this.token = this.tokenStorageService.getToken();
    this.statisticService.getStatistic(this.token, this.jstoday1, this.jstoday)
        .subscribe(
          (data: StatisticResponse[]) => {
            this.data = data;
            console.log(this.data);
            this.setUp();
          },
          error => {
            console.log(error);
          });
  }

  ngOnInit(): void {
    this.infoForm();
  }

  onChange(){
    this.disabled = false;
}

  setUp(){
    this.chartTitle = "Doanh số bán hàng theo tháng";
    this.tooltipSettings = {
      enable: true,
      format: '${point.x} : <b>${point.y}vnd</b>' 
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
    this.chartTitle1 = "Doanh số bán hàng theo quý";
    this.tooltipSettings1 = {
      enable: true,
      format: '${point.x} : <b>${point.y}vnd</b>' 
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
    this.chartTitle2 = "Doanh số bán hàng theo năm";
    this.tooltipSettings2 = {
      enable: true,
      format: '${point.x} : <b>${point.y}vnd</b>' 
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

  infoForm(){
    this.dataForm = this.fb.group({
      from: ['', [Validators.required]],  
      to: ['', [Validators.required]],
    })
  }

  get f() { return this.dataForm.controls; }

  onSubmit(){
    this.submitted = true;
    console.log(this.dataForm.value);
    if(this.dataForm.invalid){
      console.log("aaa");
      return;
    }
  }

  search(content: any){
    console.log(this.fromDate);
    console.log(this.toDate);
    if(this.fromDate == '' &&  this.toDate == '')
    {
      this.message = 'Please, input start month and end month';
      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
        console.log(this.closeResult);
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        console.log(this.closeResult);
      });
      return;
    }
    if(this.fromDate != '' &&  this.toDate == '')
    {
      this.message = 'Please, input end month';
      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
        console.log(this.closeResult);
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        console.log(this.closeResult);
      });
      return;
    }
    if(this.fromDate == '' &&  this.toDate != '')
    {
      this.message = 'Please, input start month';
      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
        console.log(this.closeResult);
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        console.log(this.closeResult);
      });
      return;
    }
    if(this.toDate != '' && this.fromDate != ''){
      var fromDate = new Date(this.fromDate);
      var toDate = new Date(this.toDate);
      if (fromDate > toDate) {
        this.message = 'The start month must be before the end month';
        this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
          this.closeResult = `Closed with: ${result}`;
          console.log(this.closeResult);
        }, (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
          console.log(this.closeResult);
        });
        return;
      }else{
        this.token = this.tokenStorageService.getToken();
        this.statisticService.getStatistic(this.token, this.fromDate, this.toDate)
            .subscribe(
              (data: StatisticResponse[]) => {
                this.data = data;
                console.log(this.data);
                this.setUp();
              },
              error => {
                console.log(error);
              });
      }
    }
  }

  searchQuater(){
    this.token = this.tokenStorageService.getToken();
    if(this.quarter1 != undefined && this.yearFrom != undefined && this.quarter2 != undefined && this.yearTo != undefined){
      this.statisticService.getStatisticByQuater(this.token, this.quarter1, this.yearFrom, this.quarter2, this.yearTo)
    .subscribe(
      (data: StatisticResponse[]) => {
        console.log(data);
        this.data1 = data;
        this.setUp1();
      },
      error => {
        console.log(error);
      });
  }
  }

  searchYear(){
    this.token = this.tokenStorageService.getToken();
    if(this.yearFrom1 != undefined && this.yearTo1 != undefined){
      this.statisticService.getStatisticByYear(this.token, this.yearFrom1, this.yearTo1)
    .subscribe(
      (data: StatisticResponse[]) => {
        console.log(data);
        this.data2 = data;
        this.setUp2();
      },
      error => {
        console.log(error);
      });
  }
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

}
