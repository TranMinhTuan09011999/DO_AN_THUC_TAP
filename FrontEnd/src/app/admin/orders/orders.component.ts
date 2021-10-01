import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { delay, map, switchMap } from 'rxjs/operators';
import { Employee } from 'src/app/model/employee';
import { Order } from 'src/app/model/order';
import { OrderDetail } from 'src/app/model/order-detail';
import { ProductDetail } from 'src/app/model/product-detail';
import { UserLogin } from 'src/app/model/userLogin';
import { InvoiceRequest } from 'src/app/request/invoice-request';
import { OrderResponse } from 'src/app/response/order-response';
import { AuthService } from 'src/app/service/auth.service';
import { CheckoutService } from 'src/app/service/checkout.service';
import { EmployeeServiceService } from 'src/app/service/employee-service.service';
import { ProductDetailService } from 'src/app/service/product-detail.service';
import { PurchaseService } from 'src/app/service/purchase.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  unconfirmOrders: Array<Order> = [];
  confirmOrders: Array<Order> = [];
  canceledOrders: Array<Order> = [];
  deliveringOrders: Array<OrderResponse> = [];
  deliveredOrders: Array<OrderResponse> = [];
  employees: Array<Employee> = [];
  token: any;
  dataForm!: FormGroup;
  submitted = false;
  orderId!: string;
  orderDetails: Array<OrderDetail> = [];
  total: number = 0;
  toDate = '';
  fromDate = '';
  closeResult!: string;
  message!: string;
  message1!: string;
  disabled = true;
  today = '';

  constructor(private modalService: NgbModal, private productDetailService: ProductDetailService, private checkoutService: CheckoutService,private employeeService: EmployeeServiceService ,private authService: AuthService, private fb: FormBuilder, private tokenStorageService: TokenStorageService, private purchaseService: PurchaseService) { }

  ngOnInit(): void {
    let today= new Date();
    this.today = formatDate(today, 'yyyy-MM-dd', 'en-VN');
    this.getAllUnconfirmOrder();
    this.getAllConfirmOrder();
    this.getAllEmployee();
    this.getAllDeliveringOrder();
    this.getAllDeliveredOrder();
    this.getAllCanceledOrder();
    this.infoForm();
  }

  infoForm(){
    this.dataForm = this.fb.group({
      deliveryEmployee: ['', [Validators.required]],  
      tax: ['', [Validators.required]],
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
    let deliveryEmployee = this.dataForm.controls.deliveryEmployee.value;
    let tax = this.dataForm.controls.tax.value;
    this.getTotal();
    this.updateOrderDelivey(this.orderId, deliveryEmployee, tax);
  }

  updateOrderDelivey(orderId: string, employeeDelivery: string, tax: string){
    this.token = this.tokenStorageService.getToken();
    let user = this.tokenStorageService.getUser();
    this.purchaseService.updateDeliveryOrder(this.token, orderId, user.id , employeeDelivery)
    .subscribe(
      (data: OrderResponse) => {
        let invoiceRequest = new InvoiceRequest();
        invoiceRequest.orderId = orderId;
        invoiceRequest.amount = this.total;
        invoiceRequest.taxId = tax;
        this.checkoutService.addReceipt(this.token, invoiceRequest)
            .subscribe(
              (data: any) => {
                this.updateQuantityProduct(orderId);
              },
              error => {
                console.log(error);
              });
      },
      error => {
        console.log(error);
      });
  }

  updateQuantityProduct(orderId: string){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getAllOrderDetailByOrderId(this.token, orderId)
    .subscribe(
      (data: OrderDetail[]) => {
        this.orderDetails = data;
        for(let orderDetail of this.orderDetails){
          this.productDetailService.updateProductDetailQuantity(this.token, orderDetail.productDetails.productDetailId, orderDetail.quantity)
          .subscribe(
            (data: ProductDetail) => {
                this.reloadPage();
            },
            error => {
              console.log(error);
            });
        }
      },
      error => {
        console.log(error);
      });
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

  getAllUnconfirmOrderByDate(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getUnconfirmOrderByDate(this.token, 1, this.fromDate, this.toDate)
        .subscribe(
          (data: Order[]) => {
            console.log(data);
            this.unconfirmOrders = data;
          },
          error => {
            console.log(error);
          });
  }

  getAllUnconfirmOrder(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getUnconfirmOrder(this.token, 1)
        .subscribe(
          (data: Order[]) => {
            this.unconfirmOrders = data;
          },
          error => {
            console.log(error);
          });
  }

  getAllCanceledOrder(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getUnconfirmOrder(this.token, 5)
        .subscribe(
          (data: Order[]) => {
            this.canceledOrders = data;
          },
          error => {
            console.log(error);
          });
  }

  getAllCanceledOrderByDate(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getUnconfirmOrderByDate(this.token, 5, this.fromDate, this.toDate)
        .subscribe(
          (data: Order[]) => {
            this.canceledOrders = data;
          },
          error => {
            console.log(error);
          });
  }

  getAllConfirmOrder(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getUnconfirmOrder(this.token, 2)
        .subscribe(
          (data: Order[]) => {
            this.confirmOrders = data;
          },
          error => {
            console.log(error);
          });
  }

  getAllConfirmOrderByDate(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getUnconfirmOrderByDate(this.token, 2, this.fromDate, this.toDate)
        .subscribe(
          (data: Order[]) => {
            this.confirmOrders = data;
          },
          error => {
            console.log(error);
          });
  }

  getAllDeliveringOrder(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getDeliveringOrder(this.token, 3)
        .subscribe(
          (data: OrderResponse[]) => {
            this.deliveringOrders = data;
          },
          error => {
            console.log(error);
          });
  }

  getAllDeliveringOrderByDate(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getDeliveringOrderByDate(this.token, 3, this.fromDate, this.toDate)
        .subscribe(
          (data: OrderResponse[]) => {
            this.deliveringOrders = data;
          },
          error => {
            console.log(error);
          });
  }

  getAllDeliveredOrder(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getDeliveringOrder(this.token, 4)
        .subscribe(
          (data: OrderResponse[]) => {
            this.deliveredOrders = data;
          },
          error => {
            console.log(error);
          });
  }

  getAllDeliveredOrderByDate(){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getDeliveringOrderByDate(this.token, 4, this.fromDate, this.toDate)
        .subscribe(
          (data: OrderResponse[]) => {
            this.deliveredOrders = data;
          },
          error => {
            console.log(error);
          });
  }

  confirm(content: any, orderId: string){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.getAllOrderDetailByOrderId(this.token, orderId)
    .subscribe(
      (data: OrderDetail[]) => {
        this.orderDetails = data;
        for(let i of this.orderDetails){
          if(i.quantity > i.productDetails.quantity){
            this.message1 = 'Xin lỗi, đơn hàng này này không được chấp nhận, vui lòng kiểm tra chi tiết đơn hàng';
            this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
              this.closeResult = `Closed with: ${result}`;
              console.log(this.closeResult);
            }, (reason) => {
              this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
              console.log(this.closeResult);
            });
            return;
          }
        }
        this.purchaseService.cancel(this.token, orderId, 2)
              .subscribe(
                (data: Order) => {
                  this.getAllUnconfirmOrder();
                  this.getAllConfirmOrder();
                  this.getAllDeliveringOrder();
                  this.getAllDeliveredOrder();
                  this.getAllCanceledOrder();
                },
                error => {
                  console.log(error);
                });
      },
      error => {
        console.log(error);
      });
  }

  cancel(orderId: string){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.cancel(this.token, orderId, 5)
    .subscribe(
      (data: Order) => {
        this.getAllUnconfirmOrder();
        this.getAllConfirmOrder();
        this.getAllDeliveringOrder();
        this.getAllDeliveredOrder();
        this.getAllCanceledOrder();
      },
      error => {
        console.log(error);
      });
  }

  delivery(orderId: string){
    this.orderId = orderId;
    console.log(this.orderId);
  }

  delivered(orderId: string){
    this.token = this.tokenStorageService.getToken();
    this.purchaseService.cancel(this.token, orderId, 4)
    .subscribe(
      (data: Order) => {
        this.getAllUnconfirmOrder();
        this.getAllConfirmOrder();
        this.getAllDeliveringOrder();
        this.getAllDeliveredOrder();
        this.getAllCanceledOrder();
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
        console.log("tuan");
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

  onChange(){
    this.disabled = false;
}

  search(content: any){
    if(this.fromDate != '' &&  this.toDate == '')
    {
      this.message = 'Vui lòng nhập ngày kết thúc';
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
        this.getAllUnconfirmOrderByDate();
        this.getAllConfirmOrderByDate();
        this.getAllDeliveringOrderByDate();
        this.getAllDeliveredOrderByDate();
        this.getAllCanceledOrderByDate();
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

  compare(quantity: number, quantity1: number): boolean{
    if(quantity <= quantity1){
      return true;
    }else{
      return false;
    }
  }

  reloadPage(): void {
    window.location.reload();
  }
}
