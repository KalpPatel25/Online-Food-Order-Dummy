import { TestBed } from '@angular/core/testing';

import { MyfilterGuard } from './myfilter.guard';

describe('MyfilterGuard', () => {
  let guard: MyfilterGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(MyfilterGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
