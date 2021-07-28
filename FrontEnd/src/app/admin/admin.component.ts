import { Category } from './../model/category';
import { TokenStorageService } from './../service/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  clickCategory = false;

  categories: Array<Category> = [];

  categoryName: string = '';
  constructor(private tokenStorageService: TokenStorageService,
              private userService: UserService) { }

  ngOnInit(): void {
    
  }

  logout(): void {
    this.tokenStorageService.signOut();
  }

}
