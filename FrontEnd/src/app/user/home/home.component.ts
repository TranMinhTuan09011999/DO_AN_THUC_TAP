
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/model/product';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { CountService } from 'src/app/service/count.service';
import { PageService } from 'src/app/service/page.service';
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
  

  constructor(private router:Router, private userService: UserService, private classBodyService: ClassBodyService, private pageService: PageService) { }

  ngOnInit(): void {
    this.pageService.changePage(this.page);
    this.classBodyService.changeClass(this.classBody);
    this.getPage();
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

  toProductGrid(){
    this.router.navigate(['/product-grid']).then(this.reloadPage);
  }

}
