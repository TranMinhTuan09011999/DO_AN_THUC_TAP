import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Provider } from 'src/app/model/provider';
import { ActiveService } from 'src/app/service/active.service';
import { ProviderService } from 'src/app/service/provider.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-list-provider',
  templateUrl: './list-provider.component.html',
  styleUrls: ['./list-provider.component.css']
})
export class ListProviderComponent implements OnInit {

  active: number = 3;
  token: any;
  providers: Array<Provider> = [];
  dataForm!: FormGroup;
  submitted = false;
  config: any;
  searchIdValue!: string;
  searchNameValue!: string;
  providerId!: string;
  provider!: Provider;

  constructor(private fb: FormBuilder, private activeService: ActiveService, private providerService : ProviderService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.activeService.changeActive(this.active);
    this.infoForm();
    this.getProvider();
  }

  infoForm(){
    this.dataForm = this.fb.group({
      providerName: ['', [Validators.required]],  
      providerAddress:  ['', [Validators.required]],
      providerEmail:  ['', [Validators.required, Validators.pattern("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")]],
      providerPhone:  ['', [Validators.required]]
    })
  }

  get f() { return this.dataForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    if(this.dataForm.invalid){
      return;
    }
    this.addProvider();
  }

  getProvider(){
    this.token = this.tokenStorageService.getToken();
    this.providerService.getProvider(this.token)
        .subscribe(
          (data: Provider[]) => {
            console.log(data);
            this.providers = data;
            console.log(this.providers);
          },
          error => {
            console.log(error);
          });

          this.config = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.providers.values.length
        };
  }

  pageChanged(event: any){
    this.config.currentPage = event;
  }

  addProvider(){
    this.token = this.tokenStorageService.getToken();
    let provider = this.dataForm.value;
    this.providerService.createProvider(this.token, provider)
        .subscribe(
          (data) => {
            this.reloadPage();
          },
          error => {
            console.log(error);
          });
  }

  showEditProvider(providerId: string){
    this.token = this.tokenStorageService.getToken();
    this.providerId = providerId;
    this.providerService.getProviderByProviderId(this.token, providerId)
        .subscribe(
          (data: Provider) => {
            this.provider = data;
            this.patchValue();
          },
          error => {
            console.log(error);
          });
  }

  patchValue(){
    this.dataForm.patchValue({
      providerName: this.provider.providerName,
      providerAddress: this.provider.providerAddress,
      providerEmail: this.provider.providerEmail,
      providerPhone: this.provider.providerPhone
    })
  }

  getProviderId(){
    this.infoForm();
  }

  onSubmit1(){
    this.submitted = true;
    console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    if(this.dataForm.invalid){
      console.log("aaa");
      return;
    }
    this.updateProvider();
  }

  updateProvider(){
    this.token = this.tokenStorageService.getToken();
    this.providerService.updateProvider(this.token, this.providerId, this.dataForm.value)
          .subscribe(
            (data: Provider) => {
              this.reloadPage();
            },
            error => {
              console.log(error);
            });
  }

  getProviderId1(providerId: string){
    this.providerId = providerId;
  }

  deleteProvider(){
    this.token = this.tokenStorageService.getToken();
    this.providerService.deleteProvider(this.token, this.providerId)
        .subscribe(
          (data) => {
            this.reloadPage();
          },
          error => {
            console.log(error);
          });
  }

  reloadPage(): void {
    window.location.reload();
  }
}
