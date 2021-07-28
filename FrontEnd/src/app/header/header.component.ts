import { AuthService } from './../service/auth.service';
import { Component, OnInit } from '@angular/core';
import { CartService } from '../service/cart.service';
import { TokenStorageService } from '../service/token-storage.service';
import { CountService } from '../service/count.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  username!: string;

  token = '';

  //count!: number;
  count!: number;
  nameUser: string = "";

  constructor(private router: Router, private tokenStorageService: TokenStorageService, private cartService: CartService, private countService: CountService) {
   }

  ngOnInit(): void {
    this.countService.currentCount.subscribe(count => this.count = count);
    const user = this.tokenStorageService.getUser();
    if(user.token != null)
    {
      this.nameUser = user.lastname + " " + user.firstname;
    }
  }

  countCartById(){
    this.token = this.tokenStorageService.getToken();
    const user = this.tokenStorageService.getUser();
    this.cartService.countCartById(this.token, user.id)
          .subscribe(
            (data) => {
              this.count = data;
            },
            error => {
              console.log(error);
            }
          );
  }

  isLoggedIn():boolean{
    this.token = this.tokenStorageService.getToken();
    if(this.token == '{}')
    {
      return false;
    }else{    
      const user = this.tokenStorageService.getUser();
      this.username = user.username;
      return true;
    }
}

  logout(): void {
    this.tokenStorageService.signOut();
  }

  directPage(){
    this.router.navigate(['../']).then(this.reloadPage);
  }

  directRegisterPage(){
    this.router.navigate(['../register']).then(this.reloadPage);
  }

  directLoginPage(){
    this.router.navigate(['../login']).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }
  
}
