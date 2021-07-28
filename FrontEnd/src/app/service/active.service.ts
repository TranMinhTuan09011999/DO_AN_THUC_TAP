import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActiveService {

  private active = new BehaviorSubject(0);
  currentActive = this.active.asObservable();
  constructor() { }

  changeActive(active: number){
    this.active.next(active);
  }
}
