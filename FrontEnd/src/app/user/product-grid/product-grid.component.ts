import { Component, OnInit } from '@angular/core';
import { ClassBodyService } from 'src/app/service/class-body.service';
import { PageService } from 'src/app/service/page.service';

@Component({
  selector: 'app-product-grid',
  templateUrl: './product-grid.component.html',
  styleUrls: ['./product-grid.component.css']
})
export class ProductGridComponent implements OnInit {
  classBody: string = "product-detail";
  page: number = 4;

  constructor(private classBodyService: ClassBodyService, private pageService: PageService) { }

  ngOnInit(): void {
    this.classBodyService.changeClass(this.classBody);
    this.pageService.changePage(this.page);
  }

}
