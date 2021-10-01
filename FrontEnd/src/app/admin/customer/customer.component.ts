import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/model/customer';
import { CustomerService } from 'src/app/service/customer.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customers: Array<Customer> = [];
  token: any;
  customerId!: string;
  searchValue!: string;
  searchValueName!: string;
  config: any;

  constructor(private customerService: CustomerService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.getAllEmployee();
  }

  getAllEmployee(){
    this.token = this.tokenStorageService.getToken();
    this.customerService.getAllCustomer(this.token)
        .subscribe(
          (data: Customer[]) => {
            this.customers = data;
          },
          error => {
            console.log(error);
          });

          this.config = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.customers.values.length
        };
  }

  pageChanged(event: any){
    this.config.currentPage = event;
  }
  

  getCustomerId(customerId: string){
    this.customerId = customerId;
  }

  deleteCustomer(){
    this.token = this.tokenStorageService.getToken();
    this.customerService.deleteCustomer(this.token, this.customerId, 0)
          .subscribe(
            (data) => {
              this.reloadPage()
            },
            error => {
              console.log(error);
            });
  }

  reloadPage(): void {
    window.location.reload();
  }

}
