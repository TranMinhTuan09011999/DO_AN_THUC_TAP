import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { LoginRequest } from 'src/app/model/login-request';
import { AuthService } from 'src/app/service/auth.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { CountService } from 'src/app/service/count.service';
import { PageService } from 'src/app/service/page.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';
declare var grecaptcha: any;


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  dataForm!: FormGroup;
  role!: string;
  token: string = '';
  IsLogin = false;
  count!: any;
  classBody: string = "user-login blog";
  page: number = 2;
  submitted = false;
  captchaError: boolean = false;
  invalidLogin: boolean = false;
  loginResponse!: string;
  message!: string;
  closeResult!: string;

  constructor(private modalService: NgbModal, private translate: TranslateService, private fb: FormBuilder, private authService: AuthService, private tokenStorage: TokenStorageService, private router:Router, private tokenStorageService: TokenStorageService, private countService: CountService, private classBodyService: ClassBodyService, private pageService: PageService) {
    translate.setDefaultLang('vn');
  }

  ngOnInit(): void {
    this.classBodyService.changeClass(this.classBody);
    this.pageService.changePage(this.page);
    this.infoForm();
  }

  infoForm(){
    /*Create Form group*/
    this.dataForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")]],   
      password: ['', [Validators.required]]
    })
  }
  get f() { return this.dataForm.controls; }

  onSubmit(content: any): void {
    console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    this.submitted = true;
    if(this.dataForm.invalid){
      return;
    }
    //const response = grecaptcha.getResponse();
    // console.log(response);
    // if (response.length === 0) {
    //   this.captchaError = true;
    //   return;
    // }
    let login = new LoginRequest();
    login.email = this.dataForm.controls.email.value;
    login.password = this.dataForm.controls.password.value;
    // login.recaptchaResponse = response;
    this.authService.login(login).subscribe(
      data => {
        if(data.status === 200) {
          this.tokenStorage.saveUser(data);        
          this.token =  this.tokenStorage.getUser().token;
          this.tokenStorage.saveToken(this.token);
          this.role = this.tokenStorage.getUser().role;
          const user = this.tokenStorageService.getUser();
          if(this.role == "ROLE_CUSTOMER")
          {
            // this.cartService.countCartById(this.token, user.id)
            //                         .subscribe(
            //                           (data) => {
            //                             this.count = data;
            //                             this.countService.changeCount(this.count);
            //                           },
            //                           error => {
            //                             console.log(error);
            //                           }
            //                         );
            this.router.navigate(['../']).then(this.reloadPage);
          }    
          else {
            this.router.navigate(['admin']).then(this.reloadPage);;
          }
        } else {
          this.invalidLogin = true;
          this.loginResponse = data.message;
        }
        grecaptcha.reset();
      },
      err => {  
        this.message = 'Email or Password is wrong!!!';
          this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
            console.log(this.closeResult);
          }, (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
            console.log(this.closeResult);
          });
          return;
        }
    );
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

  reloadPage(): void {
    window.location.reload();
  }

  toForgotPassword(){
    this.router.navigate(['../forgot-password']).then(this.reloadPage);
    this.reloadPage();
  }
  
}
