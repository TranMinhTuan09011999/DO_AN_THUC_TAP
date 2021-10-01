import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-canced-paypal',
  templateUrl: './canced-paypal.component.html',
  styleUrls: ['./canced-paypal.component.css']
})
export class CancedPaypalComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  toHome(){
    this.router.navigate(['../']).then(this.reloadPage);
  }

  toPurchase(){
    this.router.navigate(['../purchase']).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }
}
