import { Component, OnInit } from '@angular/core';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { PageService } from 'src/app/service/page.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  classBody: string = "product-detail";
  page: number = 3;

  constructor(private classBodyService: ClassBodyService, private pageService: PageService) { }

  ngOnInit(): void {
    this.classBodyService.changeClass(this.classBody);
  }

}
