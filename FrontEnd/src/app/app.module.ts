import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AdminComponent } from './admin/admin.component';
import { AdminModule } from './admin/admin.module';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './user/home/home.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { ChangeInfoComponent } from './user/change-info/change-info.component';

import { LoginGuard } from './guard/login.guard';
import { LoginInterceptor } from './interceptor/login.interceptor';
import { UserService } from './service/user.service';
import { AuthService } from './service/auth.service';
import { CountService } from './service/count.service';
import { TokenStorageService } from './service/token-storage.service';
import { AuthInterceptor } from './service/auth.interceptor';
import { ProductGridComponent } from './user/product-grid/product-grid.component';
import { ProductDetailComponent } from './user/product-detail/product-detail.component';
import { CartComponent } from './user/cart/cart.component';
import { CheckoutComponent } from './user/checkout/checkout.component';
import { PurchaseOrderComponent } from './user/purchase-order/purchase-order.component';

import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { MyAccountComponent } from './user/my-account/my-account.component';
import { NgbModal, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ForgotPasswordComponent } from './user/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './user/reset-password/reset-password.component';
import { SuccessPaypalComponent } from './user/success-paypal/success-paypal.component';
import { CancedPaypalComponent } from './user/canced-paypal/canced-paypal.component';
import { SafeUrlPipe } from './safe-url.pipe';

export function HttpLoaderFactory(http: HttpClient){
  return new TranslateHttpLoader(http);
}


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    PageNotFoundComponent,
    ChangeInfoComponent,
    ProductGridComponent,
    ProductDetailComponent,
    CartComponent,
    CheckoutComponent,
    PurchaseOrderComponent,
    MyAccountComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    SuccessPaypalComponent,
    CancedPaypalComponent,
    SafeUrlPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    AdminModule,
    BrowserAnimationsModule,
    NgxPaginationModule,
    NgbModule,
    FormsModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  // providers: [UserService, AuthService, CartService, CountService, TokenStorageService ,LoginGuard, 
  //   {provide: HTTP_INTERCEPTORS, useClass:  AuthInterceptor, multi: true}
  // ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
