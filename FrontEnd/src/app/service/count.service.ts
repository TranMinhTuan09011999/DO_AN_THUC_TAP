import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Customer } from '../model/customer';

@Injectable({
  providedIn: 'root'
})
export class CountService {
  private count = new BehaviorSubject(0);
  currentCount = this.count.asObservable();
  constructor() { }

  changeCount(count: number){
    this.count.next(count);
  }
}
