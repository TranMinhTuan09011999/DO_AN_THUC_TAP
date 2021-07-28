import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddInventoryReceivingVoucherDetailComponent } from './add-inventory-receiving-voucher-detail.component';

describe('AddInventoryReceivingVoucherDetailComponent', () => {
  let component: AddInventoryReceivingVoucherDetailComponent;
  let fixture: ComponentFixture<AddInventoryReceivingVoucherDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddInventoryReceivingVoucherDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddInventoryReceivingVoucherDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
