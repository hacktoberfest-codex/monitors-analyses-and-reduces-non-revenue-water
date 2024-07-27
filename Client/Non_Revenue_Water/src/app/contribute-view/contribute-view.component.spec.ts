import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContributeViewComponent } from './contribute-view.component';

describe('ContributeViewComponent', () => {
  let component: ContributeViewComponent;
  let fixture: ComponentFixture<ContributeViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContributeViewComponent]
    });
    fixture = TestBed.createComponent(ContributeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
