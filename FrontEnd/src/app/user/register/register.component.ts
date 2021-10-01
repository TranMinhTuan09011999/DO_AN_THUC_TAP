import { Component, OnInit } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';
import { Observable, of } from 'rxjs';
import { delay, map, switchMap } from 'rxjs/operators';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { CountService } from 'src/app/service/count.service';
import { PageService } from 'src/app/service/page.service';
import { TranslateService } from '@ngx-translate/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  dataForm!: FormGroup;
  none: boolean = true;
  role: String = "customer";
  classBody: string = "user-register blog";
  page: number = 1;
  submitted = false;
  birthday!:Date;
  message!: string;
  closeResult!: string;

  constructor(private modalService: NgbModal, private translate: TranslateService, private fb: FormBuilder, private authService: AuthService, private tokenStorage: TokenStorageService, private router:Router, private userService: UserService, private classBodyService: ClassBodyService, private pageService: PageService) {
    translate.setDefaultLang('vn');
   }
  
  ngOnInit(): void {
    this.classBodyService.changeClass(this.classBody);
    this.pageService.changePage(this.page);
    this.infoForm();
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

  get f() { return this.dataForm.controls; }

  onSubmit(content:any) {
    this.submitted = true;
    const val = this.dataForm.value;
    if(this.dataForm.invalid){
      return;
    }
    this.addData(content);
  }

  addData(content:any) {
    this.authService.register(this.dataForm.value, this.role).
    subscribe( (data: any) => {
      this.message = 'Register Account Successlly!!!';
          this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
            this.closeResult = `Closed with: ${result}`;
            this.router.navigate(['/login']).then(this.reloadPage);
            console.log(this.closeResult);
          }, (reason) => {
            this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
            console.log(this.closeResult);
          });
          return;
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

  reloadPage(): void {
    window.location.reload();
  }
  
}
