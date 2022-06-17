import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResSearchComponent } from './res-search.component';

describe('ResSearchComponent', () => {
  let component: ResSearchComponent;
  let fixture: ComponentFixture<ResSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
