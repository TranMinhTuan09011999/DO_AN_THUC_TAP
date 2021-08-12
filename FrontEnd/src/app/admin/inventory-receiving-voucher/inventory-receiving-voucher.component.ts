import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WarehouseReceiptDetail } from 'src/app/model/warehouse-receipt-detail';
import { WarehouseReceiptDetailResponse } from 'src/app/response/warehouse-receipt-detail-response';
import { WarehouseReceiptResponse } from 'src/app/response/warehouse-receipt-response';
import { ActiveService } from 'src/app/service/active.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { WarehouseReceiptService } from 'src/app/service/warehouse-receipt.service';

@Component({
  selector: 'app-inventory-receiving-voucher',
  templateUrl: './inventory-receiving-voucher.component.html',
  styleUrls: ['./inventory-receiving-voucher.component.css']
})
export class InventoryReceivingVoucherComponent implements OnInit {

  active: number = 1;
  token: any;
  public items: Array<WarehouseReceiptDetail> = [];
  warehouseReceipts: Array<WarehouseReceiptResponse> = [];
  warehouseReceiptDetails: Array<WarehouseReceiptDetailResponse> = [];

  constructor(private tokenStorageService: TokenStorageService, private activeService: ActiveService, private router: Router, private warehouseReceiptService: WarehouseReceiptService) { }

  ngOnInit(): void {
    this.activeService.changeActive(this.active);
    this.items = this.warehouseReceiptService.getItems();
    this.getAllWarehouseReceipt();
  }

  getAllWarehouseReceipt(){
    this.token = this.tokenStorageService.getToken();
    this.warehouseReceiptService.getAllWarehouseReceipt(this.token)
        .subscribe(
          (data: WarehouseReceiptResponse[]) => {
            this.warehouseReceipts = data;
          },
          error => {
            console.log(error);
          });
  }

  showWarehouseReceiptDetail(warehouseReceiptDetailId: string){
    this.token = this.tokenStorageService.getToken();
    this.warehouseReceiptService.getAllWarehouseReceiptDetailByWarehouseReceiptId(this.token, warehouseReceiptDetailId)
        .subscribe(
          (data: WarehouseReceiptDetailResponse[]) => {
            this.warehouseReceiptDetails = data;
          },
          error => {
            console.log(error);
          });
  }

  reloadPage(): void {
    window.location.reload();
  }

  toAddVoucher(){
    this.router.navigate(['admin/inventory/add']).then(this.reloadPage);
  }

}
