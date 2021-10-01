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
import { OrdersComponent } from './orders/orders.component';
import { EmployeeComponent } from './employee/employee.component';
import { CustomerComponent } from './customer/customer.component';
import { AccumulationAnnotationService, AccumulationChartModule, AccumulationDataLabelService, AccumulationLegendService, AccumulationTooltipService, CategoryService, ChartAnnotationService, ChartModule, ColumnSeriesService, DataLabelService, DateTimeService, LegendService, LineSeriesService, MultiColoredLineSeriesService, ParetoSeriesService, PieSeriesService, RangeColumnSeriesService, ScrollBarService, SplineAreaSeriesService, SplineSeriesService, StackingColumnSeriesService, StackingLineSeriesService, StepLineSeriesService, TooltipService } from '@syncfusion/ej2-angular-charts';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import { SearchfilterPipe } from './searchfilter.pipe';
import { SearchEmployeeFilterPipe } from './search-employee-filter.pipe';
import { SearchEmployeeNameFilterPipe } from './search-employee-name-filter.pipe';
import { SearchCustomerNamePipe } from './search-customer-name.pipe';
import { RevenueComponent } from './revenue/revenue.component';
import { BrowserModule } from '@angular/platform-browser';
import { SearchProviderFilterPipe } from './search-provider-filter.pipe';
import { SearchProviderNameFilterPipe } from './search-provider-name-filter.pipe';
import { SearchCategoryIdFilterPipe } from './search-category-id-filter.pipe';
import { SearchCategoryNameFilterPipe } from './search-category-name-filter.pipe';
import { SearchRoomFilterPipe } from './search-room-filter.pipe';

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
    OrdersComponent,
    EmployeeComponent,
    CustomerComponent,
    SearchfilterPipe,
    SearchEmployeeFilterPipe,
    SearchEmployeeNameFilterPipe,
    SearchCustomerNamePipe,
    RevenueComponent,
    SearchProviderFilterPipe,
    SearchProviderNameFilterPipe,
    SearchCategoryIdFilterPipe,
    SearchCategoryNameFilterPipe,
    SearchRoomFilterPipe,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgxPaginationModule, 
    Ng2SearchPipeModule,
    AccumulationChartModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    ChartModule,
    BrowserModule
  ],
  providers: [PieSeriesService, AccumulationDataLabelService, AccumulationLegendService, AccumulationTooltipService, AccumulationAnnotationService, ColumnSeriesService, CategoryService, LineSeriesService, StepLineSeriesService, SplineSeriesService, StackingLineSeriesService, DateTimeService,
    SplineAreaSeriesService, MultiColoredLineSeriesService, ParetoSeriesService, LegendService, TooltipService, DataLabelService, RangeColumnSeriesService, StackingColumnSeriesService, ScrollBarService, ChartAnnotationService],
  bootstrap: [AdminComponent]
})
export class AdminModule { }
