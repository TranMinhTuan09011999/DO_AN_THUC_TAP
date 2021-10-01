import { Component, OnInit } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { Observable, of } from 'rxjs';
import { delay, map, switchMap } from 'rxjs/operators';
import { MessageResponse } from 'src/app/response/message-response';
import { AuthService } from 'src/app/service/auth.service';
import { PageService } from 'src/app/service/page.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  dataForm!: FormGroup;
  submitted = false;
  message!: string;
  closeResult!: string;

  constructor(private translate: TranslateService, private modalService: NgbModal, private userService: UserService, private authService: AuthService, private pageService: PageService, private fb: FormBuilder) {
    translate.setDefaultLang('vn');
   }

  ngOnInit(): void {
    this.pageService.changePage(9);
    this.infoForm();
  }

  infoForm(){
    /*Create Form group*/
    this.dataForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")], [this.emailNotExistsValidator()]]
    })
  }
  get f() { return this.dataForm.controls; }

  onSubmit(content: any): void {
    this.submitted = true;
    if(this.dataForm.invalid){
      return;
    }
    let email = this.dataForm.controls.email.value;
    this.authService.sendLink(email)
        .subscribe(
          (data: MessageResponse) => {
            this.message = 'Please check your email to change password...';
            this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
              this.closeResult = `Closed with: ${result}`;
              console.log(this.closeResult);
            }, (reason) => {
              this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
              console.log(this.closeResult);
            });
            return;
          },
          error => {
            console.log(error);
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

  private emailNotExistsValidator(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return of(control.value).pipe(
        delay(500),
        switchMap((email: any) => this.userService.doesNotEmailExist(email).pipe(
          map(emailExists => emailExists ? { emailExists: true } : {})
        ))
      );
    };
  }
}
