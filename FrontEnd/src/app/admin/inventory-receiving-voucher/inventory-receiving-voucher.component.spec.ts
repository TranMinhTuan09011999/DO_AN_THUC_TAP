import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryReceivingVoucherComponent } from './inventory-receiving-voucher.component';

describe('InventoryReceivingVoucherComponent', () => {
  let component: InventoryReceivingVoucherComponent;
  let fixture: ComponentFixture<InventoryReceivingVoucherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InventoryReceivingVoucherComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryReceivingVoucherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
