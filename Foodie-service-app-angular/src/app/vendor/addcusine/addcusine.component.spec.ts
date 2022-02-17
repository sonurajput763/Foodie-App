import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddcusineComponent } from './addcusine.component';

describe('AddcusineComponent', () => {
  let component: AddcusineComponent;
  let fixture: ComponentFixture<AddcusineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddcusineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddcusineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
