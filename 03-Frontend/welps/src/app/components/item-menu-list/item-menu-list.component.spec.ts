import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemMenuListComponent } from './item-menu-list.component';

describe('ItemMenuListComponent', () => {
  let component: ItemMenuListComponent;
  let fixture: ComponentFixture<ItemMenuListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ItemMenuListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemMenuListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
