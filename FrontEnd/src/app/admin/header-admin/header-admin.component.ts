import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/service/token-storage.service';

@Component({
  selector: 'app-header-admin',
  templateUrl: './header-admin.component.html',
  styleUrls: ['./header-admin.component.css']
})
export class HeaderAdminComponent implements OnInit {

  constructor(private tokenStorageService: TokenStorageService, private router:Router) { }

  ngOnInit(): void {
  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigate(['/login']).then(this.reloadPage);
  }

  reloadPage(): void {
    window.location.reload();
  }
}
