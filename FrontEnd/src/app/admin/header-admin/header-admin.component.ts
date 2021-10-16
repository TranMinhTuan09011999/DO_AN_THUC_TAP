import { Component, OnInit } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { delay, map, switchMap } from 'rxjs/operators';
import { Chat } from 'src/app/model/chat';
import { Employee } from 'src/app/model/employee';
import { AuthService } from 'src/app/service/auth.service';
import { ChatbotService } from 'src/app/service/chatbot.service';
import { EmployeeServiceService } from 'src/app/service/employee-service.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-header-admin',
  templateUrl: './header-admin.component.html',
  styleUrls: ['./header-admin.component.css']
})
export class HeaderAdminComponent implements OnInit {

  token: any;
  employee!: Employee;
  dataForm!: FormGroup;
  birthday1!:Date;
  submitted = false;
  role!: string;
  messUserList: Array<Chat> = [];

  constructor(private chatbotService: ChatbotService, private authService: AuthService, private userService: UserService, private fb: FormBuilder, private employeeService: EmployeeServiceService, private tokenStorageService: TokenStorageService, private router:Router) { }

  ngOnInit(): void {
    const user = this.tokenStorageService.getUser();
    if(user.role == "ROLE_ADMIN"){
      this.role = "Admin"
    }else if(user.role == "ROLE_EMPLOYEE"){
      this.role = "Employee"
    }
    this.getCustomerById();
    this.infoForm();
    this.getMessUsers();
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

  get f() { return this.dataForm.controls; }

  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigate(['/login']).then(this.reloadPage);
  }

  getCustomerById(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.employeeService.getEmployeeById(this.token, user.id)
        .subscribe(
          (data: Employee) => {
            this.employee = data;
            this.patchValue();
          },
          error => {
            console.log(error);
          });
  }

  onSubmit() {
    this.submitted = true;
    const val = this.dataForm.value;
    console.log(val);
    if(this.dataForm.invalid){
      console.log("aaa");
      return;
    }
    this.updateEmployee();
  }

  patchValue(){
    this.dataForm.patchValue({
      firstname: this.employee.firstname,
      lastname: this.employee.lastname,
      gender: this.employee.gender,
      phone: this.employee.phone,
      birthday: this.employee.birthday,
      address: this.employee.address
    })
  }

  updateEmployee(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.employeeService.updateAccountEmployee(this.token, user.id, user.role, this.dataForm.value)
        .subscribe(
          (data) => {
            this.tokenStorageService.saveUser(data); 
            this.reloadPage();
          },
          error => {
            console.log(error);
          });
  }

  getMessUsers(){
    this.token = this.tokenStorageService.getToken();
    this.chatbotService.getMessUsers(this.token)
        .subscribe(
          (data: Chat[]) => {
            this.messUserList = data;
          },
          error => {
            console.log(error);
          }
        );
  }

  toChat(userId: string){
    this.router.navigate(['admin/chat/' + userId]).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }
}
