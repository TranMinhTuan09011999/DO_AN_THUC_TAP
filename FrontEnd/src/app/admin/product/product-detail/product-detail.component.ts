import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Color } from 'src/app/model/color';
import { ProductDetail } from 'src/app/model/product-detail';
import { Size } from 'src/app/model/size';
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

  productId: String = '';
  token: any;
  sizes: Array<Size> = [];
  colors: Array<Color> = [];
  imgURL: any;
  imageFile: any;
  public message!: string;
  public imagePath: any;
  dataForm!: FormGroup;
  submitted = false;
  productDetails: Array<ProductDetail> = [];

  constructor(private productDetailService: ProductDetailService, private fb: FormBuilder, private router : Router, private route: ActivatedRoute, private sizeService: SizeService, private tokenStorageService: TokenStorageService, private colorService: ColorService) { }

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['id'];
    this.getProductDetail(this.productId);
    this.getSize();
    this.getColor();
    this.infoForm();
  }

  infoForm(){
    this.dataForm = this.fb.group({
      quantity: ['', [Validators.required]],  
      price:  ['', [Validators.required]],
      sizeId:  ['', [Validators.required]],
      colorId:  ['', [Validators.required]],
      productId: this.productId,
      image: [],
    })
  }

  get f() { return this.dataForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    if(this.dataForm.invalid){
      return;
    }
    this.addProductDetail();
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
    }
  }

  getProductDetail(productId: String){
    this.token = this.tokenStorageService.getToken();
    this.productDetailService.getProductDetail(this.token, productId)
        .subscribe(
          (data: ProductDetail[]) => {
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
