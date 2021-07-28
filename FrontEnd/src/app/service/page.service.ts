import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PageService {
  private page = new BehaviorSubject(0);
  currentPage = this.page.asObservable();
  constructor() { }

  changePage(page: number){
    this.page.next(page);
  }
}
