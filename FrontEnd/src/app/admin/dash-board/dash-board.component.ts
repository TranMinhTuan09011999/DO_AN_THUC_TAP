import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActiveService } from 'src/app/service/active.service';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit {
  active!: number;

  constructor(private activeService: ActiveService, private router: Router) { }

  ngOnInit(): void {
    this.activeService.currentActive.subscribe(active => this.active = active);
  }

  toInventory(){
    this.router.navigate(['admin/inventory']).then(this.reloadPage);
  }

  toProduct(){
    this.router.navigate(['admin/product']).then(this.reloadPage);
  }

  toProvider(){
    this.router.navigate(['admin/provider']).then(this.reloadPage);
  }

  toCategory(){
    this.router.navigate(['admin/category']).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }
}
