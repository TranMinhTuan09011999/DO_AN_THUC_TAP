import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActiveService } from 'src/app/service/active.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit {
  active!: number;
  closeResult!: string;
  message!: string;

  constructor(private modalService: NgbModal, private tokenStorageService: TokenStorageService, private activeService: ActiveService, private router: Router) { }

  ngOnInit(): void {
    this.activeService.currentActive.subscribe(active => this.active = active);
    console.log(this.active);
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

  toOrders(){
    this.router.navigate(['admin/order']).then(this.reloadPage);
  }

  toEmployees(){
    this.router.navigate(['admin/employee']).then(this.reloadPage);
  }

  toCustomer(){
    this.router.navigate(['admin/customer']).then(this.reloadPage);
  }

  toHome(){
    this.router.navigate(['admin']).then(this.reloadPage);
  }

  toRevenue(content: any){
    const user = this.tokenStorageService.getUser();
    if(user.role == "ROLE_ADMIN"){
        this.router.navigate(['admin/revenue']).then(this.reloadPage);
    }else{
      this.message = 'Xin lỗi, bạn không có quyền để truy cập!!!';
      this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
        this.closeResult = `Closed with: ${result}`;
        console.log(this.closeResult);
      }, (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        console.log(this.closeResult);
      });
      return;
    }
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  reloadPage(): void {
    window.location.reload();
  }
}
