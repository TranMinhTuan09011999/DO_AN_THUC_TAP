import { ChangeDetectorRef, Component, DoCheck, OnInit } from '@angular/core';
import { AuthService } from './service/auth.service';
import { ClassBodyService } from './service/class-body.service';
import { CountService } from './service/count.service';
import { PageService } from './service/page.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

//TODO: Fix Bugs in UI of user
export class AppComponent implements OnInit{
  
  classBody!: string;
  page!: number;
  constructor(public authService: AuthService, private classBodyService: ClassBodyService, private cdref: ChangeDetectorRef, private pageService: PageService) {}
  ngOnInit(): void {
    this.classBodyService.currentClass.subscribe(classBody => this.classBody = classBody);
    this.pageService.currentPage.subscribe(page => this.page = page);
  }
  ngAfterContentChecked() {

    this.cdref.detectChanges();

  }

  reloadPage(): void {
    window.location.reload();
  }
  title = 'EcommerceWeb';
}
