
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/model/category';
import { Product } from 'src/app/model/product';
import { CategoryService } from 'src/app/service/category.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { CountService } from 'src/app/service/count.service';
import { PageService } from 'src/app/service/page.service';
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
  categories: Array<Category> = [];
  token: any;
  room: string = "Living Room";
  namePage: String = '/product-grid/';

  constructor(private tokenStorageService: TokenStorageService, private router:Router, private userService: UserService, private classBodyService: ClassBodyService, private pageService: PageService, private categoryService: CategoryService, private roomService: RoomService) { }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.classBodyService.changeClass(this.classBody);
    this.getPage();
    this.getCategory();
  }

  getPage(){
    this.userService.getPromotionProduct()
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

  toProductGrid(room: string, categoryName: string, roomId: number, categoryId: string){
    this.router.navigate(['/product-grid/' + room + "/" + categoryName], { queryParams: { roomId: roomId, categoryId: categoryId } }).then(this.reloadPage);
  }

  getCategory(){
    this.categoryService.getCategoryCustomer()
        .subscribe(
          (data: Category[]) => {
            this.categories = data;
            console.log(this.categories);
          },
          error => {
            console.log(error);
          });
  }

}
