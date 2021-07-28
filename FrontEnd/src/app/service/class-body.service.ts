import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClassBodyService {
  private classBody = new BehaviorSubject("home");
  currentClass = this.classBody.asObservable();
  constructor() { }
  changeClass(classBody: string){
    this.classBody.next(classBody);
  }
}
