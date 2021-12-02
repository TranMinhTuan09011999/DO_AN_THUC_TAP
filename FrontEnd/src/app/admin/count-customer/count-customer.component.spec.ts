import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CountCustomerComponent } from './count-customer.component';

describe('CountCustomerComponent', () => {
  let component: CountCustomerComponent;
  let fixture: ComponentFixture<CountCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CountCustomerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CountCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
