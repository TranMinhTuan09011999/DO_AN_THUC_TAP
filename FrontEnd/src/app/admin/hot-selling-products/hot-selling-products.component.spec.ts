import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotSellingProductsComponent } from './hot-selling-products.component';

describe('HotSellingProductsComponent', () => {
  let component: HotSellingProductsComponent;
  let fixture: ComponentFixture<HotSellingProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HotSellingProductsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HotSellingProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
