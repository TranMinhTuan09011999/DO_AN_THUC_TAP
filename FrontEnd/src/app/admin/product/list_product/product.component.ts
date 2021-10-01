import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Category } from 'src/app/model/category';
import { Product } from 'src/app/model/product';
import { Provider } from 'src/app/model/provider';
import { ActiveService } from 'src/app/service/active.service';
import { CategoryService } from 'src/app/service/category.service';
import { ProductService } from 'src/app/service/product.service';
import { ProviderService } from 'src/app/service/provider.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  active: number = 2;
  token: any;
  providers: Array<Provider> = [];
  categories: Array<Category> = [];
  products: Array<Product> = [];
  searchValue!: string;
  dataForm!: FormGroup;
  submitted = false;
  product!: Product;
  config: any;
  public productId!: any;
  productIdSearch: string = '';
  productNameSearch: string = '';
  categoryIdSearch: string = '';
  providerIdSearch: string = '';
  statusSearch: number = -1;

  constructor(private router: Router, private fb: FormBuilder, private activeService: ActiveService, private tokenStorageService: TokenStorageService, private providerService: ProviderService, private categoryService: CategoryService,private productService: ProductService) { }

  ngOnInit(): void {
    this.activeService.changeActive(this.active);
    this.getProvider();
    this.getCategory();
    this.getProduct();
    this.infoForm();
  }

  infoForm(){
    this.dataForm = this.fb.group({
      productName: ['', [Validators.required]],  
      status:  ['', [Validators.required]],
      description: ['', [Validators.required]],  
      providerId:  ['', [Validators.required]],
      categoryId: ['', [Validators.required]], 
    })
  }

  resetForm(){
    this.infoForm();
  }

  get f() { return this.dataForm.controls; }

  showEditProduct(productId: string){
    this.token = this.tokenStorageService.getToken();
    this.productId = productId;
    this.productService.getProductByProductId(this.token, productId)
        .subscribe(
          (data: Product) => {
            this.product = data;
            this.patchValue();
          },
          error => {
            console.log(error);
          });
  }

  patchValue(){
    this.dataForm.patchValue({
      productName: this.product.productName,
      status: this.product.status,
      description: this.product.description,
      providerId: this.product.providerDTO.providerId,
      categoryId: this.product.categoryDTO.categoryId
    })
  }

  getProvider(){
    this.token = this.tokenStorageService.getToken();
    this.providerService.getProvider(this.token)
      .subscribe(
        (data: Provider[]) => {
          this.providers = data;
          console.log(this.providers);
        },
        error => {
          console.log(error);
        });
  }

  getCategory(){
    this.token = this.tokenStorageService.getToken();
    this.categoryService.getCategory(this.token)
      .subscribe(
        (data) => {
          this.categories = data;
        },
        error => {
          console.log(error);
        });
  }

  onSubmit(): void {
    this.submitted = true;
    console.log(this.dataForm.value);
    if(this.dataForm.invalid){
      console.log("aaa");
      return;
    }
    this.addProduct();
  }

  onSubmit1(){
    this.submitted = true;
    console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    if(this.dataForm.invalid){
      console.log("aaa");
      return;
    }
    this.updateProduct();
  }

  updateProduct(){
    this.token = this.tokenStorageService.getToken();
    this.productService.updateProduct(this.token, this.productId, this.dataForm.value)
          .subscribe(
            (data: Product) => {
              this.reloadPage();
            },
            error => {
              console.log(error);
            });
  }

  getProduct(){
    this.token = this.tokenStorageService.getToken();
    this.productService.getProduct(this.token)
        .subscribe(
          (data: Product[]) => {
            this.products = data;
          },
          error => {
            console.log(error);
          });

          this.config = {
            itemsPerPage: 5,
            currentPage: 1,
            totalItems: this.products.values.length
        };
  }

  addProduct(){
    this.token = this.tokenStorageService.getToken();
    let provider = this.dataForm.value;
    this.productService.createProduct(this.token, provider)
        .subscribe(
          (data) => {
            this.reloadPage();
          },
          error => {
            console.log(error);
          });
  }

  toProductDetail(productId: string){
    this.router.navigate(['admin/product/' + productId]).then(this.reloadPage);
  }

  pageChanged(event: any){
    this.config.currentPage = event;
  }

  search(){
    this.token = this.tokenStorageService.getToken();
    console.log(this.productIdSearch);
    console.log(this.productNameSearch);
    this.productService.getProductSearch(this.token, this.productIdSearch, this.productNameSearch, this.categoryIdSearch, this.providerIdSearch, this.statusSearch)
      .subscribe(
        (data: Product[]) => {
          this.products = data;
        },
        error => {
          console.log(error);
        });
  }

  reloadPage(): void {
    window.location.reload();
  }

}
