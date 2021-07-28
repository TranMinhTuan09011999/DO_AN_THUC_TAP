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


  constructor(private fb: FormBuilder, private authService: AuthService, private tokenStorage: TokenStorageService, private router:Router, private userService: UserService, private classBodyService: ClassBodyService, private pageService: PageService) { }
  
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

  onSubmit() {
    this.submitted = true;
    const val = this.dataForm.value;
    console.log(val);
    this.addData();
  }

  addData() {
    this.authService.register(this.dataForm.value, this.role).
    subscribe( (data: any) => {
      console.log("Registion success");
      this.router.navigate(['/login']).then(this.reloadPage);
    });
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
