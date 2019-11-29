import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainphotodisplayComponent } from './mainphotodisplay.component';

describe('MainphotodisplayComponent', () => {
  let component: MainphotodisplayComponent;
  let fixture: ComponentFixture<MainphotodisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainphotodisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainphotodisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
