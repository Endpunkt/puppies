import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PuppyDetailsComponent } from './puppy-details.component';

describe('ItemDetailComponent', () => {
  let component: PuppyDetailsComponent;
  let fixture: ComponentFixture<PuppyDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PuppyDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PuppyDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
