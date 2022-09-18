import { TestBed } from '@angular/core/testing';

import { UtilsInterceptor } from './utils.interceptor';

describe('UtilsInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      UtilsInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: UtilsInterceptor = TestBed.inject(UtilsInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
