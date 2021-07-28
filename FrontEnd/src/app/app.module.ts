import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
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
import { CartService } from './service/cart.service';
import { CountService } from './service/count.service';
import { TokenStorageService } from './service/token-storage.service';
import { AuthInterceptor } from './service/auth.interceptor';
import { ProductGridComponent } from './user/product-grid/product-grid.component';
import { ProductDetailComponent } from './user/product-detail/product-detail.component';


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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    AdminModule,
    BrowserAnimationsModule,
    NgxPaginationModule
  ],
  // providers: [UserService, AuthService, CartService, CountService, TokenStorageService ,LoginGuard, 
  //   {provide: HTTP_INTERCEPTORS, useClass:  AuthInterceptor, multi: true}
  // ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
