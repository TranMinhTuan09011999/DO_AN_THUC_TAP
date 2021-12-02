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
import { formatDate, PlatformLocation } from '@angular/common';
import { TranslateService } from '@ngx-translate/core';
import { CategoryService } from 'src/app/service/category.service';
import { Category } from 'src/app/model/category';
import { Room } from 'src/app/model/room';
import { RoomService } from 'src/app/service/room.service';

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
  jstoday = '';
  categories: Array<Category> = [];
  rooms: Array<Room> = [];

  constructor(location: PlatformLocation, private roomService: RoomService, private categoryService: CategoryService, private translate: TranslateService, private cart: CartService, private router:Router, private colorService: ColorService, private sizeService: SizeService, private pageService: PageService, private route: ActivatedRoute, private productDetailService: ProductDetailService) {
    translate.setDefaultLang('vn');
    location.onPopState(() => {
      this.reloadPage();
    });  
   }

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
     this.getCategory();
     this.getRoom();
     //this.getColorByProductIdAndSizeId();
     //this.getColorIdBySizeId(this.sizeId);
  }

  getProductDetailWithProductIdAndColorIdAndSizeId(){
    this.productDetailService.getProducDetailWithProductIdAndColorIdAndSizeId(this.productId, this.colorId, this.sizeId)
    .subscribe(
      (data: ProductWithRoomIdAndCategoryId[]) => {
        console.log(data);
        this.product = data[0];
        let today= new Date();
        this.jstoday = formatDate(today, 'yyyy-MM-dd HH:mm:ss', 'en-VN');
        console.log(this.jstoday);
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
    console.log("aaaaaa");
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

  compareDate(dateOfBegin: string, dateOfEnd: string, today:string):number{
    let date = new Date(dateOfBegin);
    let date1 = new Date(dateOfEnd);
    let date2 = new Date(today);
    console.log(date);
    console.log(date1);
    console.log(date2);
    if(date < date2 && date1 > date2){
      return 1;
    }
    return 0;
  }

  compareDateOfBeginAndToday(dateOfBegin: string, today:string):number{
    let date = Date.parse(dateOfBegin);
    let date1 = Date.parse(today);
    if(date < date1){
      return 1;
    }
    return 0;
  }

  compareDateOfEndAndToday(dateOfEnd: string, today:string):number{
    let date = Date.parse(dateOfEnd);
    let date1 = Date.parse(today);
    if(date > date1){
      return 1;
    }
    return 0;
  }

  toProductGrid(room: string, categoryName: string, roomId: number, categoryId: string){
    this.router.navigate(['/product-grid/' + room + "/" + categoryName], { queryParams: { roomId: roomId, categoryId: categoryId } }).then(this.reloadPage);
  }

  getCategory(){
    this.categoryService.getCategoryCustomer()
        .subscribe(
          (data: Category[]) => {
            this.categories = data;
            console.log("aaaaaaaaaaaaaaaaaaaaaaaaaa");
            console.log(this.categories);
          },
          error => {
            console.log(error);
          });
  }

  getRoom(){
    this.roomService.getRoom()
      .subscribe(
        (data) => {
          this.rooms = data;
        },
        error => {
          console.log(error);
        });
  }

}
