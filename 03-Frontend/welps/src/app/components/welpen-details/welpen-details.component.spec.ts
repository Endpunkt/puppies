import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WelpenDetailsComponent } from './welpen-details.component';

describe('WelpenDetailsComponent', () => {
  let component: WelpenDetailsComponent;
  let fixture: ComponentFixture<WelpenDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WelpenDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WelpenDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
