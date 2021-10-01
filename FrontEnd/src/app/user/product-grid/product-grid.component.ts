import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { Category } from 'src/app/model/category';
import { Color } from 'src/app/model/color';
import { ProductWithRoomIdAndCategoryId } from 'src/app/model/product-with-room-id-and-category-id';
import { Room } from 'src/app/model/room';
import { CategoryService } from 'src/app/service/category.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { ColorService } from 'src/app/service/color.service';
import { PageService } from 'src/app/service/page.service';
import { ProductService } from 'src/app/service/product.service';
import { RoomService } from 'src/app/service/room.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-product-grid',
  templateUrl: './product-grid.component.html',
  styleUrls: ['./product-grid.component.css']
})
export class ProductGridComponent implements OnInit {
  classBody: string = "product-detail";
  page: number = 4;
  room!: string;
  categoryName!: string;
  roomId!: number;
  categoryId!: string;
  products: Array<ProductWithRoomIdAndCategoryId> = [];
  config: any;
  config1: any;
  colors: Array<Color> = [];
  token: any;
  rooms: Array<Room> = [];
  @ViewChild('min') min!:ElementRef; 
  @ViewChild('max') max!:ElementRef; 
  categories: Array<Category> = [];

  constructor(private translate: TranslateService, private colorService: ColorService, private tokenStorageService: TokenStorageService, private route: ActivatedRoute, private classBodyService: ClassBodyService, private pageService: PageService, private categoryService: CategoryService, private roomService: RoomService, private productService: ProductService, private router:Router) {
    translate.setDefaultLang('vn');
   }

  ngOnInit(): void {
    this.classBodyService.changeClass(this.classBody);
    this.pageService.changePage(this.page);
    this.room = this.route.snapshot.params['room'];
    this.categoryName = this.route.snapshot.params['category'];
    this.route.queryParams.subscribe(params => {
      this.roomId = params['roomId'];
      this.categoryId = params['categoryId'];
     });
     this.getRoom();
     this.getCategory();
     this.getColor();
     this.getProductWithRoomIdAndCategoryIdOderByProductId(this.roomId, this.categoryId);
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

  getColor(){
    this.colorService.getColorCustomer()
          .subscribe(
            (data: Color[]) => {
              console.log(data);
              this.colors = data;
              console.log(this.products);
            },
            error => {
              console.log(error);
            });
  }

  getProductSearchPrice(){
    var min = Number(this.min.nativeElement.value);
    var max = Number(this.max.nativeElement.value);
    this.productService.getProductWithRoomIdAndCategoryIdByPrice(this.roomId, this.categoryId, min, max)
        .subscribe(
          (data: ProductWithRoomIdAndCategoryId[]) => {
            console.log(data);
            this.products = data;
            console.log(this.products);
          },
          error => {
            console.log(error);
          });

          this.config = {
            itemsPerPage: 9,
            currentPage: 1,
            totalItems: this.products.values.length
        };
    
        this.config1 = {
          itemsPerPage: 9,
          currentPage: 1,
          totalItems: this.products.values.length
      };
  }

  getProductWithRoomIdAndCategoryIdAndColorId(colorId: number){
    this.productService.getProductWithRoomIdAndCategoryIdAndColorId(this.roomId, this.categoryId, colorId)
      .subscribe(
        (data: ProductWithRoomIdAndCategoryId[]) => {
          console.log(data);
          this.products = data;
          console.log(this.products);
        },
        error => {
          console.log(error);
        });

      this.config = {
        itemsPerPage: 9,
        currentPage: 1,
        totalItems: this.products.values.length
    };

    this.config1 = {
      itemsPerPage: 9,
      currentPage: 1,
      totalItems: this.products.values.length
  };
  }

  getProductWithRoomIdAndCategoryIdOderByProductId(roomId: number, categoryId: string){
    this.productService.getProductWithRoomIdAndCategoryId(roomId, categoryId, 1)
    .subscribe(
      (data: ProductWithRoomIdAndCategoryId[]) => {
        console.log(data);
        this.products = data;
        console.log(this.products);
      },
      error => {
        console.log(error);
      });

      this.config = {
        itemsPerPage: 9,
        currentPage: 1,
        totalItems: this.products.values.length
    };

    this.config1 = {
      itemsPerPage: 9,
      currentPage: 1,
      totalItems: this.products.values.length
  };
  }

  toProductGrid(room: string, categoryName: string, roomId: number, categoryId: string){
    this.router.navigate(['/product-grid/' + room + "/" + categoryName], { queryParams: { roomId: roomId, categoryId: categoryId } }).then(this.reloadPage);
  }

  toProductDetail(productId: string, colorId: number, sizeId: number, room: string, categoryName: string, productnName: string)
  {
      this.router.navigate(['/product-detail/' + room + "/" + categoryName + "/" + productnName], { queryParams: { productId: productId, colorId: colorId, sizeId: sizeId} }).then(this.reloadPage);
  }

  pageChanged(event: any){
    this.config.currentPage = event;
  }

  pageChanged1(event: any){
    this.config1.currentPage = event;
  }

  onChange(select: string){
      if(select == "1"){
        this.getProductWithRoomIdAndCategoryIdOderByProductId(this.roomId, this.categoryId);
      }else if(select == "2"){
        this.orderByNameAZ();
      }else if(select == "3"){
        this.orderByNameZA();
      }else if(select == "4"){
        this.orderByPriceLowToHigh();
      }else if(select == "5"){
        this.orderByPriceHighToLow();
      }
  }

  orderByNameAZ(){
    console.log("lllllllllllllllllllllllllll");
    this.productService.getProductWithRoomIdAndCategoryId(this.roomId, this.categoryId, 2)
        .subscribe(
          (data: ProductWithRoomIdAndCategoryId[]) => {
            console.log(data);
            this.products = data;
            console.log(this.products);
          },
          error => {
            console.log(error);
          });

          this.config = {
            itemsPerPage: 9,
            currentPage: 1,
            totalItems: this.products.values.length
        };

      this.config1 = {
        itemsPerPage: 9,
        currentPage: 1,
        totalItems: this.products.values.length
      };
  }

  orderByNameZA(){
    console.log("lllllllllllllllllllllllllll");
    this.productService.getProductWithRoomIdAndCategoryId(this.roomId, this.categoryId, 3)
        .subscribe(
          (data: ProductWithRoomIdAndCategoryId[]) => {
            console.log(data);
            this.products = data;
            console.log(this.products);
          },
          error => {
            console.log(error);
          });

          this.config = {
            itemsPerPage: 9,
            currentPage: 1,
            totalItems: this.products.values.length
        };

      this.config1 = {
        itemsPerPage: 9,
        currentPage: 1,
        totalItems: this.products.values.length
      };
  }

  orderByPriceLowToHigh(){
    console.log("lllllllllllllllllllllllllll");
    this.productService.getProductWithRoomIdAndCategoryId(this.roomId, this.categoryId, 4)
        .subscribe(
          (data: ProductWithRoomIdAndCategoryId[]) => {
            console.log(data);
            this.products = data;
            console.log(this.products);
          },
          error => {
            console.log(error);
          });

          this.config = {
            itemsPerPage: 9,
            currentPage: 1,
            totalItems: this.products.values.length
        };

      this.config1 = {
        itemsPerPage: 9,
        currentPage: 1,
        totalItems: this.products.values.length
      };
  }

  orderByPriceHighToLow(){
    console.log("lllllllllllllllllllllllllll");
    this.productService.getProductWithRoomIdAndCategoryId(this.roomId, this.categoryId, 5)
        .subscribe(
          (data: ProductWithRoomIdAndCategoryId[]) => {
            console.log(data);
            this.products = data;
            console.log(this.products);
          },
          error => {
            console.log(error);
          });

          this.config = {
            itemsPerPage: 9,
            currentPage: 1,
            totalItems: this.products.values.length
        };

      this.config1 = {
        itemsPerPage: 9,
        currentPage: 1,
        totalItems: this.products.values.length
      };
  }

  reloadPage(): void {
    window.location.reload();
  }

  
}
