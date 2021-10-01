import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TranslateService } from '@ngx-translate/core';
import { MessageResponse } from 'src/app/response/message-response';
import { AuthService } from 'src/app/service/auth.service';
import { PageService } from 'src/app/service/page.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  dataForm!: FormGroup;
  submitted = false;
  token!: string;
  message!: string;
  closeResult!: string;

  constructor(private router:Router, private translate: TranslateService, private modalService: NgbModal, private authService: AuthService, private fb: FormBuilder, private pageService: PageService, private route: ActivatedRoute) {
    translate.setDefaultLang('vn');
   }

  ngOnInit(): void {
    this.pageService.changePage(9);
    this.route.queryParams.subscribe(params => {
      this.token = params['token'];
     });
    this.infoForm();
  }

  infoForm(){
    /*Create Form group*/
    this.dataForm = this.fb.group({
      password: ['', [Validators.required]],
      pwdd: ['', [Validators.required]],
    },
    {
      validators: this.MustMatch('password', 'pwdd')
    })
  }

  get f() { return this.dataForm.controls; }

  onSubmit(content: any): void {
    this.submitted = true;
    if(this.dataForm.invalid){
      return;
    }
    let pass = this.dataForm.controls.password.value;
    this.authService.reset(this.token, pass)
        .subscribe(
          (data: MessageResponse) => {
            this.message = 'Change password successly...';
            this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
              this.closeResult = `Closed with: ${result}`;
              console.log(this.closeResult);
              this.router.navigate(['/login']).then(this.reloadPage);
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
