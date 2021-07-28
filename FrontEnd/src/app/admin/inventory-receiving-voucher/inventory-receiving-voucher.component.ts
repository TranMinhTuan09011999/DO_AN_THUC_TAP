import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActiveService } from 'src/app/service/active.service';

@Component({
  selector: 'app-inventory-receiving-voucher',
  templateUrl: './inventory-receiving-voucher.component.html',
  styleUrls: ['./inventory-receiving-voucher.component.css']
})
export class InventoryReceivingVoucherComponent implements OnInit {

  active: number = 1;

  constructor(private activeService: ActiveService, private router: Router) { }

  ngOnInit(): void {
    this.activeService.changeActive(this.active);
  }

  reloadPage(): void {
    window.location.reload();
  }

  toAddVoucher(){
    this.router.navigate(['admin/inventory/add']).then(this.reloadPage);
  }

}
