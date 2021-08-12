import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { CartComponent } from './user/cart/cart.component';
import { ChangeInfoComponent } from './user/change-info/change-info.component';
import { CheckoutComponent } from './user/checkout/checkout.component';
import { HomeComponent } from './user/home/home.component';
import { LoginComponent } from './user/login/login.component';
import { ProductDetailComponent } from './user/product-detail/product-detail.component';
import { ProductGridComponent } from './user/product-grid/product-grid.component';
import { PurchaseOrderComponent } from './user/purchase-order/purchase-order.component';
import { RegisterComponent } from './user/register/register.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'changeInfo', component: ChangeInfoComponent },
  { path: 'product-grid/:room/:category', component: ProductGridComponent },
  { path: 'product-detail/:room/:category/:productName', component: ProductDetailComponent },
  { path: 'cart', component: CartComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'purchase', component: PurchaseOrderComponent },
  //TODO: Add interceptor and Page Not Found Page
  // { path: '**', component: PageNotFoundComponent},
  { path: '', redirectTo: 'admin', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
