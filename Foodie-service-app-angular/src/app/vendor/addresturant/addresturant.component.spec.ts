import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddresturantComponent } from './addresturant.component';

describe('AddresturantComponent', () => {
  let component: AddresturantComponent;
  let fixture: ComponentFixture<AddresturantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddresturantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddresturantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
