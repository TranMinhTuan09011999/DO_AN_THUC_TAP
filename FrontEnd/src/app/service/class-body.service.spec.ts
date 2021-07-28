import { TestBed } from '@angular/core/testing';

import { ClassBodyService } from './class-body.service';

describe('ClassBodyService', () => {
  let service: ClassBodyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClassBodyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
