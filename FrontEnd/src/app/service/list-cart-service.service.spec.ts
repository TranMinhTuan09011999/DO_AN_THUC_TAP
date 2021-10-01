import { TestBed } from '@angular/core/testing';

import { ListCartServiceService } from './list-cart-service.service';

describe('ListCartServiceService', () => {
  let service: ListCartServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListCartServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
