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
    this.chartTitle = "Doanh số bán hàng hàng tháng";
    this.tooltipSettings = {
      enable: true,
      format: '${point.x} : <b>${point.y}$</b>' 
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
