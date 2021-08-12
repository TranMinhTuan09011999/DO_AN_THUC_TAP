import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ProductWithRoomIdAndCategoryId } from 'src/app/model/product-with-room-id-and-category-id';
import { CategoryService } from 'src/app/service/category.service';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { PageService } from 'src/app/service/page.service';
import { ProductService } from 'src/app/service/product.service';
import { RoomService } from 'src/app/service/room.service';

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

  constructor(private route: ActivatedRoute, private classBodyService: ClassBodyService, private pageService: PageService, private categoryService: CategoryService, private roomService: RoomService, private productService: ProductService, private router:Router) { }

  ngOnInit(): void {
    this.classBodyService.changeClass(this.classBody);
    this.pageService.changePage(this.page);
    this.room = this.route.snapshot.params['room'];
    this.categoryName = this.route.snapshot.params['category'];
    this.route.queryParams.subscribe(params => {
      this.roomId = params['roomId'];
      this.categoryId = params['categoryId'];
     });
     this.getProductWithRoomIdAndCategoryId(this.roomId, this.categoryId);
  }

  getProductWithRoomIdAndCategoryId(roomId: number, categoryId: string){
    this.productService.getProductWithRoomIdAndCategoryId(roomId, categoryId)
    .subscribe(
      (data: ProductWithRoomIdAndCategoryId[]) => {
        console.log(data);
        this.products = data;
        console.log(this.products);
      },
      error => {
        console.log(error);
      });
  }

  toProductDetail(productId: string, colorId: number, sizeId: number, room: string, categoryName: string, productnName: string)
  {
      this.router.navigate(['/product-detail/' + room + "/" + categoryName + "/" + productnName], { queryParams: { productId: productId, colorId: colorId, sizeId: sizeId} }).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }
}
