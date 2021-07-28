import { Category } from './../../../model/category';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { ActiveService } from 'src/app/service/active.service';
import { RoomService } from 'src/app/service/room.service';
import { Room } from 'src/app/model/room';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-list-categories',
  templateUrl: './list-categories.component.html',
  styleUrls: ['./list-categories.component.css']
})
export class ListCategoriesComponent implements OnInit {

  active: number = 4;
  token: any;
  rooms: Array<Room> = [];
  dataForm!: FormGroup;
  submitted = false;
  categories: Array<Category> = [];

  constructor(private fb: FormBuilder, private userService: UserService, private tokenStorageService: TokenStorageService, private activeService: ActiveService, private roomService: RoomService, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.activeService.changeActive(this.active);
    this.getCategory();
    this.getRoom();
    this.infoForm();
  }

  getRoom(){
    this.token = this.tokenStorageService.getToken();
    this.roomService.getRoom(this.token)
      .subscribe(
        (data) => {
          this.rooms = data;
        },
        error => {
          console.log(error);
        });
  }

  infoForm(){
    this.dataForm = this.fb.group({
      categoryName: ['', [Validators.required]],  
      room:  ['', [Validators.required]]
    })
  }

  get f() { return this.dataForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    if(this.dataForm.invalid){
      return;
    }
    this.addCategory();
  }

  addCategory(){
    this.token = this.tokenStorageService.getToken();
    let category = this.dataForm.value;
    this.categoryService.createCategory(this.token, category)
        .subscribe(
          (data) => {
            this.reloadPage();
          },
          error => {
            console.log(error);
          });
  }

  getCategory(){
    this.token = this.tokenStorageService.getToken();
    this.categoryService.getCategory(this.token)
        .subscribe(
          (data: Category[]) => {
            this.categories = data;
            console.log(this.categories);
          },
          error => {
            console.log(error);
          });
  }

  reloadPage(): void {
    window.location.reload();
  }
}
