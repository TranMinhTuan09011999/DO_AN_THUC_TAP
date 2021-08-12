import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Color } from 'src/app/model/color';
import { Product } from 'src/app/model/product';
import { ProductWithRoomIdAndCategoryId } from 'src/app/model/product-with-room-id-and-category-id';
import { Size } from 'src/app/model/size';
import { UserLogin } from 'src/app/model/userLogin';
import { WarehouseReceiptDetail } from 'src/app/model/warehouse-receipt-detail';
import { WarehouseReceiptDetailRequest } from 'src/app/request/warehouse-receipt-detail-request';
import { ColorService } from 'src/app/service/color.service';
import { ProductDetailService } from 'src/app/service/product-detail.service';
import { ProductService } from 'src/app/service/product.service';
import { SizeService } from 'src/app/service/size.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { WarehouseReceiptService } from 'src/app/service/warehouse-receipt.service';

@Component({
  selector: 'app-add-inventory-receiving-voucher-detail',
  templateUrl: './add-inventory-receiving-voucher-detail.component.html',
  styleUrls: ['./add-inventory-receiving-voucher-detail.component.css']
})
export class AddInventoryReceivingVoucherDetailComponent implements OnInit {

  token: any;
  products: Array<Product> = [];
  sizes: Array<Size> = [];
  colors: Array<Color> = [];
  WarehouseReceiptDetails: Array<WarehouseReceiptDetail> = [];
  productId: any;
  dataForm!: FormGroup;
  submitted = false;
  message!: any;
  public items: Array<WarehouseReceiptDetail> = [];
  product!: ProductWithRoomIdAndCategoryId;

  constructor(private router: Router, private productDetailService: ProductDetailService, private colorService: ColorService, private sizeService: SizeService, private fb: FormBuilder, private tokenStorageService: TokenStorageService, private productService:ProductService, private warehouseReceiptService: WarehouseReceiptService) { }

  ngOnInit(): void {
    this.getProductId();
    this.getSize();
    this.getColor();
    this.items = this.warehouseReceiptService.getItems();
    this.infoForm();
  }

  getProductId(){
    this.token = this.tokenStorageService.getToken();
    this.productService.getProduct(this.token)
    .subscribe(
      (data) => {
        this.products = data;
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

  infoForm(){
    this.dataForm = this.fb.group({
      productId: ['', [Validators.required]],  
      sizeId:  ['', [Validators.required]],
      colorId: ['', [Validators.required]],  
      quantity:  ['', [Validators.required]],
      price: ['', [Validators.required]], 
    })
  }

  get f() { return this.dataForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    if(this.dataForm.invalid){
      return;
    }
    this.token = this.tokenStorageService.getToken();
    
    let productId = this.dataForm.controls.productId.value;
    let sizeId = this.dataForm.controls.sizeId.value;
    let colorId = this.dataForm.controls.colorId.value;
    let quantity = this.dataForm.controls.quantity.value;
    let price = this.dataForm.controls.price.value;
    this.productDetailService.ckeckProductExist(this.token,productId, sizeId, colorId)
    .subscribe(
      (data) => {
        if(data == true)
        {
          console.log("Exist");
          let productInfo = new WarehouseReceiptDetail();
          productInfo.productId = productId;
          productInfo.sizeId = sizeId;
          productInfo.colorId = colorId;
          productInfo.quantity = quantity;
          productInfo.price = price;
          let color!: Color;
          let size!: Size;
          this.sizeService.getSizeBySizeId(this.token, sizeId)
              .subscribe(
                (data: Size) => {
                  console.log(data);
                  size = data;
                  productInfo.size = size.size;
                  this.colorService.getColorByColorId(this.token, colorId)
                      .subscribe(
                        (data: Color) => {
                          console.log(data);
                          color = data;
                          productInfo.color = color.colorName;
                          console.log(productInfo);
                          this.warehouseReceiptService.add(productInfo);
                          this.reloadPage();
                        },
                        error => {
                          console.log(error);
                        });
                },
                error => {
                  console.log(error);
                });        
          
          


        }else{
          this.message = "*** Product does not exist";
          return;
        }
      },
      error => {
        console.log(error);
      });
  }

  ckeckProductExist(){
    this.token = this.tokenStorageService.getToken();
  }

  expense(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    console.log(user.id);
    let warehouse;
    warehouse = JSON.parse(localStorage.getItem('warehouse') || '{}');
    let total = 0;
    for(let i in warehouse){
      total += warehouse[i].price * warehouse[i].quantity;
    }
    this.warehouseReceiptService.createWarehouseReceipt(this.token, user.id, total)
        .subscribe(
          (data) => {
            let id = data;
            console.log(id);
            let warehouse;
            warehouse = JSON.parse(localStorage.getItem('warehouse') || '{}');
            for(let i in warehouse){
              let productId = warehouse[i].productId;
              let sizeId = warehouse[i].sizeId;
              let colorId = warehouse[i].colorId;
              let quantity = warehouse[i].quantity;
              let price = warehouse[i].price;
              this.productDetailService.getProducDetailWithProductIdAndColorIdAndSizeId(productId, colorId, sizeId)
                  .subscribe(
                    (data: ProductWithRoomIdAndCategoryId[]) => {
                      console.log(data);
                      this.product = data[0];
                      console.log(this.product)
                      let value = new WarehouseReceiptDetailRequest;
                      value.productDetailId = this.product.productDetailId;
                      value.warehouseReceiptId = id;
                      value.quantity = quantity;
                      value.price = price;
                      this.warehouseReceiptService.createWarehouseReceiptDetail(this.token, value)
                      .subscribe(
                        (data) => {
                          this.warehouseReceiptService.clearCart();
                          this.router.navigate(['admin/inventory']).then(this.reloadPage);
                        },
                        error => {
                          console.log(error);
                        });
                    },
                    error => {
                      console.log(error);
                    });
            }
            
          },
          error => {
            console.log(error);
          });
  }

  onChange(value: string){
    const valueNumber = parseInt(value);
  }

  reloadPage(): void {
    window.location.reload();
  }
}
