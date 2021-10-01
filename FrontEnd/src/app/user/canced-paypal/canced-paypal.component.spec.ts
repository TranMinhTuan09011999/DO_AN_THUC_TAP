import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancedPaypalComponent } from './canced-paypal.component';

describe('CancedPaypalComponent', () => {
  let component: CancedPaypalComponent;
  let fixture: ComponentFixture<CancedPaypalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancedPaypalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CancedPaypalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
