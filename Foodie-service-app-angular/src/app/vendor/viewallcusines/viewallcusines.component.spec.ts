import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewallcusinesComponent } from './viewallcusines.component';

describe('ViewallcusinesComponent', () => {
  let component: ViewallcusinesComponent;
  let fixture: ComponentFixture<ViewallcusinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewallcusinesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewallcusinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
