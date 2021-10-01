import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Items } from '../response/items';

@Injectable({
  providedIn: 'root'
})
export class ListCartServiceService {

  private cartDataSource = new BehaviorSubject<Items[]>([]);
  currentCart = this.cartDataSource.asObservable();

  private price = new BehaviorSubject(0);
  currentPrice = this.price.asObservable();

  constructor() { }

  changeCart(items: Items[]){
    this.cartDataSource.next(items);
  }

  changePrice(price: number){
    this.price.next(price);
  }
}
