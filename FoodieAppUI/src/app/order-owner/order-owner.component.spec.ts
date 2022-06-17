import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderOwnerComponent } from './order-owner.component';

describe('OrderOwnerComponent', () => {
  let component: OrderOwnerComponent;
  let fixture: ComponentFixture<OrderOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
