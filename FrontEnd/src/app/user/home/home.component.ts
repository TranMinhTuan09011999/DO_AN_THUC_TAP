
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Category } from 'src/app/model/category';
import { Product } from 'src/app/model/product';
import { ProductWithRoomIdAndCategoryId } from 'src/app/model/product-with-room-id-and-category-id';
import { NewProductResponse } from 'src/app/response/new-product-response';
import { CategoryService } from 'src/app/service/category.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { CountService } from 'src/app/service/count.service';
import { PageService } from 'src/app/service/page.service';
import { ProductService } from 'src/app/service/product.service';
import { RoomService } from 'src/app/service/room.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  classBody: string = "home";
  products: Array<Product> = [];
  page: number = 0;
  categoriesByLivingRoom: Array<Category> = [];
  categoriesByKitchen: Array<Category> = [];
  token: any;
  room: string = "Living Room";
  kitchenRoom: string = "Kitchen";
  namePage: String = '/product-grid/';
  newProductByLivingRooms: Array<ProductWithRoomIdAndCategoryId> = [];
  discountProductByLivingRooms: Array<ProductWithRoomIdAndCategoryId> = [];
  newProductByKitchens: Array<ProductWithRoomIdAndCategoryId> = [];
  discountProductByKitchens: Array<ProductWithRoomIdAndCategoryId> = [];

  constructor(private translate: TranslateService, private tokenStorageService: TokenStorageService, private router:Router, private userService: UserService, private classBodyService: ClassBodyService, private pageService: PageService, private categoryService: CategoryService, private roomService: RoomService, private productService: ProductService) { 
  }

  getPriceByLang():number{
    if(this.translate.currentLang == "en"){
      return 1;
    }else{
      return 2;
    }
  }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.classBodyService.changeClass(this.classBody);
    this.getCategoryByLivingRoom();
    this.getCategoryByKitchen();
    this.getTop8NewProductByLivingRoom();
    this.getTop8DiscountProductByLivingRoom();
    this.getTop8NewProductByKitchen();
    this.getTop8DiscountProductByKitchen();
  }

  reloadPage(): void {
    window.location.reload();
  }

  toProductGrid(room: string, categoryName: string, roomId: number, categoryId: string){
    this.router.navigate(['/product-grid/' + room + "/" + categoryName], { queryParams: { roomId: roomId, categoryId: categoryId } }).then(this.reloadPage);
  }

  getCategoryByLivingRoom(){
    this.categoryService.getCategoryByRoom(1)
        .subscribe(
          (data: Category[]) => {
            this.categoriesByLivingRoom = data;
          },
          error => {
            console.log(error);
          });
  }

  getCategoryByKitchen(){
    this.categoryService.getCategoryByRoom(2)
        .subscribe(
          (data: Category[]) => {
            this.categoriesByKitchen = data;
          },
          error => {
            console.log(error);
          });
  }

  getTop8NewProductByLivingRoom(){
    this.productService.getTop8NewProduct(1)
        .subscribe(
          (data: ProductWithRoomIdAndCategoryId[]) => {
            this.newProductByLivingRooms = data;
          },
          error => {
            console.log(error);
          });
  }

  getTop8NewProductByKitchen(){
    this.productService.getTop8NewProduct(2)
        .subscribe(
          (data: ProductWithRoomIdAndCategoryId[]) => {
            this.newProductByKitchens = data;
          },
          error => {
            console.log(error);
          });
  }

  getTop8DiscountProductByLivingRoom(){
    this.productService.getTop8DiscountProduct(1)
        .subscribe(
          (data: ProductWithRoomIdAndCategoryId[]) => {
            this.discountProductByLivingRooms = data;
          },
          error => {
            console.log(error);
          });
  }

  getTop8DiscountProductByKitchen(){
    this.productService.getTop8DiscountProduct(2)
        .subscribe(
          (data: ProductWithRoomIdAndCategoryId[]) => {
            this.discountProductByKitchens = data;
          },
          error => {
            console.log(error);
          });
  }

  toProductDetail(productId: string, colorId: number, sizeId: number, room: string, categoryName: string, productnName: string)
  {
      this.router.navigate(['/product-detail/' + room + "/" + categoryName + "/" + productnName], { queryParams: { productId: productId, colorId: colorId, sizeId: sizeId} }).then(this.reloadPage);
  }

}
