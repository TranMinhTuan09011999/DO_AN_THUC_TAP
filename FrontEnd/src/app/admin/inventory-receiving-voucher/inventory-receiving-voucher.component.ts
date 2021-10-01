import { formatDate } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Employee } from 'src/app/model/employee';
import { WarehouseReceiptDetail } from 'src/app/model/warehouse-receipt-detail';
import { WarehouseReceiptDetailResponse } from 'src/app/response/warehouse-receipt-detail-response';
import { WarehouseReceiptResponse } from 'src/app/response/warehouse-receipt-response';
import { ActiveService } from 'src/app/service/active.service';
import { EmployeeServiceService } from 'src/app/service/employee-service.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { WarehouseReceiptService } from 'src/app/service/warehouse-receipt.service';

@Component({
  selector: 'app-inventory-receiving-voucher',
  templateUrl: './inventory-receiving-voucher.component.html',
  styleUrls: ['./inventory-receiving-voucher.component.css']
})
export class InventoryReceivingVoucherComponent implements OnInit {

  active: number = 1;
  token: any;
  public items: Array<WarehouseReceiptDetail> = [];
  warehouseReceipts: Array<WarehouseReceiptResponse> = [];
  warehouseReceiptDetails: Array<WarehouseReceiptDetailResponse> = [];
  config: any;
  PNId = '';
  employeeId = '';
  toDate = '';
  fromDate = '';
  employees: Array<Employee> = [];
  disabled = true;
  today = '';
  dateFail = false;
  closeResult!: string;
  message!: string;
  total: number = 0;

  constructor(private modalService: NgbModal, private employeeService : EmployeeServiceService, private tokenStorageService: TokenStorageService, private activeService: ActiveService, private router: Router, private warehouseReceiptService: WarehouseReceiptService) { }

  ngOnInit(): void {
    this.activeService.changeActive(this.active);
    this.items = this.warehouseReceiptService.getItems();
    this.getAllWarehouseReceipt();
    this.getAllEmployee();
    let today= new Date();
    this.today = formatDate(today, 'yyyy-MM-dd', 'en-VN');
  }

  onChange(){
      this.disabled = false;
  }

  search(content: any){
    if(this.fromDate != '' &&  this.toDate == '')
    {
      this.message = 'Vui lòng, nhập ngày kết thúc';
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
        this.message = 'Ngày bắt đầu phải trước ngày kết thúc';
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
        this.warehouseReceiptService.getAllWarehouseReceiptBySearch(this.token, this.PNId, this.employeeId, this.fromDate, this.toDate)
                  .subscribe(
                    (data: WarehouseReceiptResponse[]) => {
                      this.warehouseReceipts = data;
                    },
                    error => {
                      console.log(error);
                    });
          console.log(this.toDate);
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

  getAllEmployee(){
    this.token = this.tokenStorageService.getToken();
    this.employeeService.getAllEmployee(this.token)
        .subscribe(
          (data: Employee[]) => {
            this.employees = data;
          },
          error => {
            console.log(error);
          });
  }

  getAllWarehouseReceipt(){
    this.token = this.tokenStorageService.getToken();
    this.warehouseReceiptService.getAllWarehouseReceipt(this.token)
        .subscribe(
          (data: WarehouseReceiptResponse[]) => {
            this.warehouseReceipts = data;
          },
          error => {
            console.log(error);
          });

          this.config = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.warehouseReceipts.values.length
        };
  }

  pageChanged(event: any){
    this.config.currentPage = event;
  }

  showWarehouseReceiptDetail(warehouseReceiptDetailId: string){
    this.token = this.tokenStorageService.getToken();
    this.warehouseReceiptService.getAllWarehouseReceiptDetailByWarehouseReceiptId(this.token, warehouseReceiptDetailId)
        .subscribe(
          (data: WarehouseReceiptDetailResponse[]) => {
            this.warehouseReceiptDetails = data;
            this.getTotal();
          },
          error => {
            console.log(error);
          });
  }

  reloadPage(): void {
    window.location.reload();
  }

  toAddVoucher(){
    this.router.navigate(['admin/inventory/add']).then(this.reloadPage);
  }

  getTotal(){
    this.total = 0;
    for(let orderDetail of this.warehouseReceiptDetails){
        this.total += orderDetail.price*orderDetail.quantity;
      }
    }
  }
