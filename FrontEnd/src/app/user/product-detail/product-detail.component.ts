import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { reduce } from 'rxjs/operators';
import { ColorByProductIdAndSizeId } from 'src/app/model/color-by-product-id-and-size-id';
import { ProductDetail } from 'src/app/model/product-detail';
import { ProductWithRoomIdAndCategoryId } from 'src/app/model/product-with-room-id-and-category-id';
import { SizeByProductId } from 'src/app/model/size-by-product-id';
import { CartResponse } from 'src/app/response/cart-response';
import { CartService } from 'src/app/service/cart.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { ColorService } from 'src/app/service/color.service';
import { PageService } from 'src/app/service/page.service';
import { ProductDetailService } from 'src/app/service/product-detail.service';
import { SizeService } from 'src/app/service/size.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  page: number = 5;
  room!: string;
  categoryName!: string;
  productId!: string;
  colorId!: number;
  sizeId!: number;
  product!: ProductWithRoomIdAndCategoryId;
  colors: Array<ColorByProductIdAndSizeId> = [];
  sizes: Array<SizeByProductId> = [];
  colorIdNew!: number;
  productDetailList:  Array<ProductDetail> = [];
  productDetailId!: number;
  qtyAddedToCart = 0;
  quantity: number = 1;

  constructor(private cart: CartService, private router:Router, private colorService: ColorService, private sizeService: SizeService, private pageService: PageService, private route: ActivatedRoute, private productDetailService: ProductDetailService) { }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.room = this.route.snapshot.params['room'];
    this.categoryName = this.route.snapshot.params['category'];
    this.route.queryParams.subscribe(params => {
      this.productId = params['productId'];
      this.colorId = params['colorId'];
      this.sizeId = params['sizeId'];
     });
     this.getProductDetailWithProductIdAndColorIdAndSizeId();
  }

  getProductDetailWithProductIdAndColorIdAndSizeId(){
    this.productDetailService.getProducDetailWithProductIdAndColorIdAndSizeId(this.productId, this.colorId, this.sizeId)
    .subscribe(
      (data: ProductWithRoomIdAndCategoryId[]) => {
        this.product = data[0];
        this.getColorByProductIdAndSizeId();
        this.getSizeByProductId();
      },
      error => {
        console.log(error);
      });
  }

  getColorByProductIdAndSizeId(){
    this.colorService.getColorByProductIdAndSizeId(this.productId, this.sizeId, this.colorId)
    .subscribe(
      (data: ColorByProductIdAndSizeId[]) => {
        this.colors = data;
      },
      error => {
        console.log(error);
      });
  }

  getSizeByProductId(){
    this.sizeService.getSizeByProductId(this.productId, this.sizeId)
    .subscribe(
      (data: SizeByProductId[]) => {
        this.sizes = data;
      },
      error => {
        console.log(error);
      });
  }

  toProductDetail(productId: string, colorId: number, sizeId: number, room: string, categoryName: string, productnName: string)
  {
      this.router.navigate(['/product-detail/' + room + "/" + categoryName + "/" + productnName], { queryParams: { productId: productId, colorId: colorId, sizeId: sizeId} }).then(this.reloadPage);
  }

  onChange(value: string){
    const valueNumber = parseInt(value);
    this.getColorIdBySizeId(valueNumber);
  }

  getColorIdBySizeId(sizeId: number)
  {
    this.productDetailService.getProductDetailTop1BySizeId(sizeId)
        .subscribe(
          (data: ProductDetail[]) => {
            this.productDetailList = data;
            this.productDetailList.forEach(element => {
              this.colorIdNew = element.color.colorId;
            });
            this.toProductDetail(this.productId, this.colorIdNew, sizeId, this.room, this.categoryName, this.product.productName);
          },
          error => {
            console.log(error);
          });
  }

  addToCart(): void{
    let productInfo = new CartResponse();
    console.log(this.product);
    console.log(this.product.productDetailId);
    this.productDetailService.getProductDetailByProductDetailId(this.product.productDetailId)
    .subscribe(
      (data: CartResponse) => {
        productInfo = data;
        console.log(productInfo);
        this.cart.add(productInfo, this.quantity);
        this.router.navigate(['cart']).then(this.reloadPage);
      },
      error => {
        console.log(error);
      });
  }

  reloadPage(): void {
    window.location.reload();
  }

  increaseQuantity(){
    this.quantity++;
  }

  deincreaseQuantity(){
    if(this.quantity > 1){
      this.quantity--;
    }
  }

}
