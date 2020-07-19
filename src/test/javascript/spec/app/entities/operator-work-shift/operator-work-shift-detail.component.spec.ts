import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IfmSimple1TestModule } from '../../../test.module';
import { OperatorWorkShiftDetailComponent } from 'app/entities/operator-work-shift/operator-work-shift-detail.component';
import { OperatorWorkShift } from 'app/shared/model/operator-work-shift.model';

describe('Component Tests', () => {
  describe('OperatorWorkShift Management Detail Component', () => {
    let comp: OperatorWorkShiftDetailComponent;
    let fixture: ComponentFixture<OperatorWorkShiftDetailComponent>;
    const route = ({ data: of({ operatorWorkShift: new OperatorWorkShift(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [OperatorWorkShiftDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OperatorWorkShiftDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OperatorWorkShiftDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load operatorWorkShift on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.operatorWorkShift).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
