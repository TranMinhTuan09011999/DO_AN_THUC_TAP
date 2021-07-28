import { HomeComponent } from './home/home.component';
import { ListCategoriesComponent } from './categories/list-categories/list-categories.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { UsersComponent } from './user_/users/users.component';
import { EditUserComponent } from './user_/edit-user/edit-user.component';
import { HeaderAdminComponent } from './header-admin/header-admin.component';
import { DashBoardComponent } from './dash-board/dash-board.component';
import { InventoryReceivingVoucherComponent } from './inventory-receiving-voucher/inventory-receiving-voucher.component';
import { AddInventoryReceivingVoucherDetailComponent } from './add-inventory-receiving-voucher-detail/add-inventory-receiving-voucher-detail.component';
import { ListProviderComponent } from './provider/list-provider/list-provider.component';
import { ProductComponent } from './product/list_product/product.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';


@NgModule({
  declarations: [
    AdminComponent,
    ListCategoriesComponent,
    UsersComponent,
    EditUserComponent,
    HomeComponent,
    HeaderAdminComponent,
    DashBoardComponent,
    InventoryReceivingVoucherComponent,
    AddInventoryReceivingVoucherDetailComponent,
    ProductComponent,
    ListProviderComponent,
    ProductDetailComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule, 
    Ng2SearchPipeModule,
  ],
  providers: [],
  bootstrap: [AdminComponent]
})
export class AdminModule { }
