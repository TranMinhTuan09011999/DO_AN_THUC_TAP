import { Component, OnInit } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { delay, map, switchMap } from 'rxjs/operators';
import { Employee } from 'src/app/model/employee';
import { AuthService } from 'src/app/service/auth.service';
import { EmployeeServiceService } from 'src/app/service/employee-service.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  employees: Array<Employee> = [];
  token: any;
  searchValue!: string;
  searchValueName!: string;
  config: any;
  birthday!:Date;
  submitted = false;
  dataForm!: FormGroup;
  role: String = "employee";
  employeeId!: string;
  closeResult!: string;
  message!: string;
  message1!: string;
  show = true;
  mess = true;

  constructor(private modalService: NgbModal, private authService: AuthService, private userService: UserService, private fb: FormBuilder, private employeeService: EmployeeServiceService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.getAllEmployee();
    this.infoForm();
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

          this.config = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.employees.values.length
        };
  }

  pageChanged(event: any){
    this.config.currentPage = event;
  }

  infoForm(){
    /*Create Form group*/
    this.birthday = new Date();
    this.dataForm = this.fb.group({
      firstname: ['', [Validators.required]],
      lastname: ['', [Validators.required]],  
      gender:  ['', [Validators.required]],
      phone: ['', [Validators.required, Validators.pattern("^[_0-9]{10}")]],
      birthday:  ['', [Validators.required]],
      email: ['', [Validators.required, Validators.pattern("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")], [this.emailExistsValidator()]],    
      address: ['', [Validators.required]],
      password: ['', [Validators.required]],
      pwdd: ['', [Validators.required]],
    },
    {
      validators: this.MustMatch('password', 'pwdd')
    })
  }

  onSubmit() {
    this.submitted = true;
    if(this.dataForm.invalid){
      return;
    }
    this.addData();
  }

  addEmployee(content: any){
    const user = this.tokenStorageService.getUser();
    if(user.role == "ROLE_EMPLOYEE"){
      this.message = 'Xin lỗi, bạn không có quyền để truy cập!!!';
      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
        console.log(this.closeResult);
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        console.log(this.closeResult);
      });
      this.show = false;
      return;
    }
  }

  addData() {
    const user = this.tokenStorageService.getUser();
    this.authService.register(this.dataForm.value, this.role).
            subscribe( (data: any) => {
              console.log("Registion success");
              this.reloadPage();
            });
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

  get f() { return this.dataForm.controls; }

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

  MustMatch(controlName: string, matchingControlName:string){
    return(formGroup: FormGroup)=>{
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];
      if(matchingControl.errors && !matchingControl.errors.MustMatch){
        return;
      }
      if(control.value !== matchingControl.value){
        matchingControl.setErrors({MustMatch:true});
      }else{
        matchingControl.setErrors(null);
      }
    }
  }

  getEmployeeId(content: any, employeeId: string){
    const user = this.tokenStorageService.getUser();
    if(user.role == "ROLE_ADMIN"){
      this.employeeId = employeeId;
      this.mess = true;
    }else{
      this.message1 = 'Xin lỗi, bạn không có quyền để truy cập!!!';
      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
        console.log(this.closeResult);
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        console.log(this.closeResult);
      });
      this.mess = false;
      return;
    }
  }

  deleteEmployee(){
    this.token = this.tokenStorageService.getToken();
    this.employeeService.deleteEmployee(this.token, this.employeeId, 0)
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
