import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCenarioComponent } from './list-cenario.component';

describe('ListCenarioComponent', () => {
  let component: ListCenarioComponent;
  let fixture: ComponentFixture<ListCenarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListCenarioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListCenarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
