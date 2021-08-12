import { TestBed } from '@angular/core/testing';

import { WarehouseReceiptService } from './warehouse-receipt.service';

describe('WarehouseReceiptService', () => {
  let service: WarehouseReceiptService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WarehouseReceiptService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
