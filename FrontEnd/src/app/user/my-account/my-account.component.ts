import { Component, OnInit } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { delay, map, switchMap } from 'rxjs/operators';
import { Customer } from 'src/app/model/customer';
import { CustomerService } from 'src/app/service/customer.service';
import { PageService } from 'src/app/service/page.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent implements OnInit {

  page: number = 8;
  token: any;
  customer!: Customer;
  dataForm!: FormGroup;
  birthday1!:Date;
  submitted = false;

  constructor(private router: Router, private userService: UserService, private fb: FormBuilder, private pageService: PageService, private customerService: CustomerService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.getCustomerById();
    this.infoForm();
  }

  getCustomerById(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.customerService.getCustomerById(this.token, user.id)
        .subscribe(
          (data: Customer) => {
            this.customer = data;
            this.patchValue();
            console.log(this.customer);
          },
          error => {
            console.log(error);
            this.router.navigate(['../pageNotFound']).then(this.reloadPage);
          });
  }

  infoForm(){
    /*Create Form group*/
    this.birthday1 = new Date();
    this.dataForm = this.fb.group({
      firstname: ['', [Validators.required]],
      lastname: ['', [Validators.required]],  
      gender:  ['', [Validators.required]],
      phone: ['', [Validators.required, Validators.pattern("^[_0-9]{10}")]],
      birthday:  ['', [Validators.required]],   
      address: ['', [Validators.required]]
    })
  }

  patchValue(){
    this.dataForm.patchValue({
      firstname: this.customer.firstname,
      lastname: this.customer.lastname,
      gender: this.customer.gender,
      phone: this.customer.phone,
      birthday: this.customer.birthday,
      address: this.customer.address
    })
  }

  get f() { return this.dataForm.controls; }

  onSubmit() {
    this.submitted = true;
    const val = this.dataForm.value;
    console.log(val);
    if(this.dataForm.invalid){
      console.log("aaa");
      return;
    }
    this.updateCustomer();
  }

  updateCustomer(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.customerService.updateAccountCustomer(this.token, user.id, user.role, this.dataForm.value)
        .subscribe(
          (data) => {
            this.tokenStorageService.saveUser(data); 
            this.reloadPage();
          },
          error => {
            console.log(error);
          });
  }

  reloadPage(): void {
    window.location.reload();
  }

  private emailExistsValidator(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return of(control.value).pipe(
        delay(500),
        switchMap((email: any) => this.userService.doesEmailExist(email).pipe(
          map(emailExists => emailExists ? { emailExists: true } : null)
        ))
      );
    };
  }

}
