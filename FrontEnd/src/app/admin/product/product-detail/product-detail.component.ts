import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Color } from 'src/app/model/color';
import { ProductDetail } from 'src/app/model/product-detail';
import { Size } from 'src/app/model/size';
import { CartResponse } from 'src/app/response/cart-response';
import { ColorService } from 'src/app/service/color.service';
import { ProductDetailService } from 'src/app/service/product-detail.service';
import { SizeService } from 'src/app/service/size.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  productId: string = '';
  token: any;
  sizes: Array<Size> = [];
  colors: Array<Color> = [];
  imgURL: any;
  imageFile: any;
  public message!: string;
  public imagePath: any;
  dataForm!: FormGroup;
  dataForm1!: FormGroup;
  submitted = false;
  submitted1 = false;
  productDetails: Array<ProductDetail> = [];
  public productDetailId!: any;
  public productDetail!: ProductDetail;
  hasImage = false;
  sizeId = 0; 
  colorId = 0;

  constructor(private productDetailService: ProductDetailService, private fb: FormBuilder, private router : Router, private route: ActivatedRoute, private sizeService: SizeService, private tokenStorageService: TokenStorageService, private colorService: ColorService) { }

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['id'];
    this.getProductDetail(this.productId);
    this.getSize();
    this.getColor();
    this.infoForm();
    this.infoForm1();
  }

  infoForm(){
    this.dataForm = this.fb.group({
      quantity: ['', [Validators.required]],  
      price:  ['', [Validators.required]],
      sizeId:  ['', [Validators.required]],
      colorId:  ['', [Validators.required]],
      discount:  0,
      productId: this.productId,
      image: [],
    })
  }

  infoForm1(){
    this.dataForm1 = this.fb.group({
      quantity: ['', [Validators.required]],  
      price:  ['', [Validators.required]],
      sizeId:  ['', [Validators.required]],
      colorId:  ['', [Validators.required]],
      discount:  ['', [Validators.required]],
      productId: this.productId,
      image: [],
    })
  }

  search(){
    console.log(this.sizeId);
    console.log(this.colorId);
    this.token = this.tokenStorageService.getToken();
    this.productDetailService.getProductDetailBySearch(this.token, this.productId, this.sizeId, this.colorId)
          .subscribe(
            (data: ProductDetail[]) => {
              this.productDetails = data;
            },
            error => {
              console.log(error);
            });
  }

  get f() { return this.dataForm.controls; }
  get f1() { return this.dataForm1.controls; }

  onSubmit(): void {
    this.submitted = true;
    if(this.dataForm.invalid){
      return;
    }
    this.addProductDetail();
  }

  onSubmit1(){
    this.submitted1 = true;
    if(this.dataForm1.invalid){
      return;
    }
    if(this.hasImage == false)
    {
      this.updateProductDetailWithoutImage();
    }else{
      this.updateProductDetail();
    }
  }

  showEditProductDetail(productDetailId: number){
    this.token = this.tokenStorageService.getToken();
    this.productDetailId = productDetailId;
    this.productDetailService.getProductDetailWithProductDetailId(this.productDetailId)
        .subscribe(
          (data: ProductDetail) => {
            this.productDetail = data;
            console.log(this.productDetail)
            this.patchValue();
          },
          error => {
            console.log(error);
          });
  }

  updateProductDetailWithoutImage(){
    this.token = this.tokenStorageService.getToken();
    this.productDetailService.updateProductDetailWithoutImage(this.token, this.productDetailId, this.dataForm1.value)
          .subscribe(
            (data) => {
              this.reloadPage();
            },
            error => {
              console.log(error);
            });
  }

  updateProductDetail(){
    this.token = this.tokenStorageService.getToken();
    const productDetail = this.dataForm1.value;
    console.log(productDetail);
    const formData = new FormData();
    formData.append('productDetail',JSON.stringify(productDetail));
    formData.append('file',this.imageFile);
    this.productDetailService.updateProductDetail(this.token, this.productDetailId, formData)
          .subscribe(
            (data) => {
              this.reloadPage();
            },
            error => {
              console.log(error);
            });
  }

  patchValue(){
    this.dataForm1.patchValue({
      quantity: this.productDetail.quantity,
      price: this.productDetail.price,
      sizeId: this.productDetail.size.sizeId,
      colorId: this.productDetail.color.colorId,
      discount: this.productDetail.discount,
      image: this.productDetail.image
    })
  }

  addProductDetail() {
    this.token = this.tokenStorageService.getToken();
    const productDetail = this.dataForm.value;
    console.log(productDetail);
    const formData = new  FormData();
    formData.append('productDetail',JSON.stringify(productDetail));
    formData.append('file',this.imageFile);
    this.productDetailService.createProductDetail(this.token, formData)
        .subscribe(
          (data) => {
            this.reloadPage();
          },
          error => {
            console.log(error);
          });
  }

  getSize(){
    this.token = this.tokenStorageService.getToken();
    this.sizeService.getSize(this.token)
        .subscribe(
          (data: Size[]) => {
            console.log(data);
            this.sizes = data;
          },
          error => {
            console.log(error);
          });
  }

  getColor(){
    this.token = this.tokenStorageService.getToken();
    this.colorService.getColor(this.token)
        .subscribe(
          (data: Color[]) => {
            this.colors = data;
          },
          error => {
            console.log(error);
          });
  }

  onSelectFile(event:any){
    if(event.target.files.length >0){
      const file = event.target.files[0];
      this.imageFile = file;
      // this.f['image'].setValue(file);

      var mimeType = event.target.files[0].type;
      if(mimeType.match(/image\/*/) == null){
        this.message = "Only images are supported";
        return;
      }
      var reader = new FileReader();
      this.imagePath = file;
      reader.readAsDataURL(file);
      reader.onload = (_event) => {
        this.imgURL = reader.result;
        console.log(this.imgURL);
      }
      this.hasImage = true;
    }
  }

  getProductDetail(productId: String){
    this.token = this.tokenStorageService.getToken();
    this.productDetailService.getProductDetail(this.token, productId)
        .subscribe(
          (data: ProductDetail[]) => {
            console.log(data);
            this.productDetails = data;
          },
          error => {
            console.log(error);
          });
  }

  reloadPage(): void {
    window.location.reload();
  }

}
