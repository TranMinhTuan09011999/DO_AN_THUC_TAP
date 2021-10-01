import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Color } from 'src/app/model/color';
import { Product } from 'src/app/model/product';
import { ProductDetail } from 'src/app/model/product-detail';
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
  dataForm1!: FormGroup;
  submitted = false;
  submitted1 = false;
  message!: any;
  message1!: any;
  message2!: any;
  public items: Array<WarehouseReceiptDetail> = [];
  product!: ProductWithRoomIdAndCategoryId;
  closeResult!: string;
  closeResult1!: string;
  productDetailId!: number;

  constructor(private modalService: NgbModal, private router: Router, private productDetailService: ProductDetailService, private colorService: ColorService, private sizeService: SizeService, private fb: FormBuilder, private tokenStorageService: TokenStorageService, private productService:ProductService, private warehouseReceiptService: WarehouseReceiptService) { }

  ngOnInit(): void {
    this.getProductId();
    this.getSize();
    this.getColor();
    this.items = this.warehouseReceiptService.getItems();
    this.infoForm();
    this.infoForm1();
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

  infoForm1(){
    this.dataForm1 = this.fb.group({
      productId: ['', [Validators.required]],  
      sizeId:  ['', [Validators.required]],
      colorId: ['', [Validators.required]],  
      quantity:  ['', [Validators.required]],
      price: ['', [Validators.required]], 
    })
  }

  get f() { return this.dataForm.controls; }
  get f1() { return this.dataForm1.controls; }

  onSubmit(content: any): void {
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
          this.productDetailService.getProducDetailWithProductIdAndColorIdAndSizeId(productId, colorId, sizeId)
                .subscribe(
                  (data: ProductWithRoomIdAndCategoryId[]) => {
                    let productInfo = new WarehouseReceiptDetail();
                    productInfo.productId = data[0].productId;
                    productInfo.productDetailId = data[0].productDetailId;
                    productInfo.sizeId =  data[0].sizeId;
                    productInfo.colorId =  data[0].colorId;
                    productInfo.size = data[0].size;
                    productInfo.color = data[0].colorName;
                    productInfo.quantity = quantity;
                    productInfo.price = price;
                    this.warehouseReceiptService.add(productInfo);
                    this.reloadPage();
                  },
                  error => {
                    console.log(error);
                  });
        }else{
          this.message = 'Sản phẩm không tồn tại...';
            this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
              this.closeResult = `Closed with: ${result}`;
              console.log(this.closeResult);
            }, (reason) => {
              this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
              console.log(this.closeResult);
            });
          return;
        }
      },
      error => {
        console.log(error);
      });
  }

  onSubmit1(): void {
    this.submitted1 = true;
    if(this.dataForm1.invalid){
      return;
    }
    let quantity = this.dataForm1.controls.quantity.value;
    let price = this.dataForm1.controls.price.value;
    this.updateQtyAndPrice(this.productDetailId, quantity, price);
    this.reloadPage();
  }

  updateQtyAndPrice(productDetailId: number, quantity: number, price: number)
  {
    let shopping_cart;
    shopping_cart = JSON.parse(localStorage.getItem('warehouse') || '{}' );
    for(let i in shopping_cart){
      if(productDetailId == shopping_cart[i].productDetailId){
        shopping_cart[i].quantity = quantity;
        shopping_cart[i].price = price;
        break;
      }
    }
    localStorage.setItem('warehouse', JSON.stringify(shopping_cart));
  }

  private getDismissReason1(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
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

  ckeckProductExist(){
    this.token = this.tokenStorageService.getToken();
  }

  showEditProductDetail(productDetailId: number){
    this.productDetailId = productDetailId;
    let shopping_cart;
    let index;
    shopping_cart = JSON.parse(localStorage.getItem('warehouse') || '{}');
    for(let i in shopping_cart){
      if (productDetailId == shopping_cart[i].productDetailId)
      {
        this.patchValue(shopping_cart[i]);
      }
    }
  }

  patchValue(productDetail: any){
    this.dataForm1.patchValue({
      productId: productDetail.productId,
      sizeId: productDetail.sizeId,
      colorId: productDetail.colorId,
      quantity: productDetail.quantity,
      price: productDetail.price
    })
    this.dataForm1.get('productId')?.disable();
    this.dataForm1.get('sizeId')?.disable();
    this.dataForm1.get('colorId')?.disable();
  }

  getProductDetailId(content:any, productDetailId: number){
    this.productDetailId = productDetailId;
    this.message2 = 'Bạn có chắc chắn xóa sản phẩm này không?';
            this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
              this.closeResult1 = `Closed with: ${result}`;
              this.deleteItemByProductDetail();
              console.log(this.closeResult);
            }, (reason) => {
              this.closeResult1 = `Dismissed ${this.getDismissReason1(reason)}`;
              console.log(this.closeResult1);
            });
  }

  deleteItemByProductDetail(){
    this.deleteItem(this.productDetailId);
    this.items = this.warehouseReceiptService.getItems();
  }

  expense(content: any){
    let warehouse;
    warehouse = JSON.parse(localStorage.getItem('warehouse') || '{}');
    console.log(warehouse.length)
    if(warehouse.length == 0 || warehouse.length == undefined){
      this.message1 = 'Vui lòng nhập thông tin các sản phẩm nhập...';
            this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
              this.closeResult1 = `Closed with: ${result}`;
              console.log(this.closeResult);
            }, (reason) => {
              this.closeResult1 = `Dismissed ${this.getDismissReason(reason)}`;
              console.log(this.closeResult1);
            });
          return;
    }
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    console.log(user.id);
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
                          this.productDetailService.updateProductDetailWithQuantityAndPrice(this.token, value.productDetailId, value.quantity, value.price)
                              .subscribe(
                                (data: ProductDetail) => {
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

  deleteItem(productDetailId: number){
    let shopping_cart;
    let index;
    shopping_cart = JSON.parse(localStorage.getItem('warehouse') || '{}');
    for(let i in shopping_cart){
      if (productDetailId == shopping_cart[i].productDetailId)
      {
        index = i;
        console.log(index);
      }
    }
    shopping_cart.splice(index, 1);
    localStorage.setItem('warehouse', JSON.stringify(shopping_cart));
  }

  reloadPage(): void {
    window.location.reload();
  }
}
