import { ListCategoriesComponent } from './categories/list-categories/list-categories.component';
import { AdminComponent } from './admin.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './user_/users/users.component';
import { EditUserComponent } from './user_/edit-user/edit-user.component';
import { PageNotFoundComponent } from '../page-not-found/page-not-found.component';
import { InventoryReceivingVoucherComponent } from './inventory-receiving-voucher/inventory-receiving-voucher.component';
import { AddInventoryReceivingVoucherDetailComponent } from './add-inventory-receiving-voucher-detail/add-inventory-receiving-voucher-detail.component';
import { ListProviderComponent } from './provider/list-provider/list-provider.component';
import { ProductComponent } from './product/list_product/product.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { OrdersComponent } from './orders/orders.component';
import { EmployeeComponent } from './employee/employee.component';
import { CustomerComponent } from './customer/customer.component';
import { HomeComponent } from './home/home.component';
import { RevenueComponent } from './revenue/revenue.component';
import { ChatComponent } from './chat/chat.component';
import { HotSellingProductsComponent } from './hot-selling-products/hot-selling-products.component';
import { CountCustomerComponent } from './count-customer/count-customer.component';
import { ProfitComponent } from './profit/profit.component';

const routes: Routes = [
  { path: 'admin', component: AdminComponent, 
      children: [
        {path: '', component: HomeComponent},
        {path: 'product', component: ProductComponent},
        {path: 'category', component: ListCategoriesComponent},
        {path: 'users', component: UsersComponent},
        {path: 'edit-user/:id', component: EditUserComponent},
        {path: 'inventory', component: InventoryReceivingVoucherComponent },
        {path: 'inventory/add', component: AddInventoryReceivingVoucherDetailComponent },
        {path: 'provider', component: ListProviderComponent },
        {path: 'product/:id', component: ProductDetailComponent },
        {path: 'order', component: OrdersComponent },
        {path: 'employee', component: EmployeeComponent},
        {path: 'customer', component: CustomerComponent},
        {path: 'revenue', component: RevenueComponent},
        {path: 'chat/:userId', component: ChatComponent},
        {path: 'hotSellingProducts', component: HotSellingProductsComponent},  
        {path: 'countCustomer', component: CountCustomerComponent},   
        {path: 'profit', component: ProfitComponent},
        // { path: '**', component: PageNotFoundComponent}
      ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
