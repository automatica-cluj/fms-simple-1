import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { IfmSimple1TestModule } from '../../../test.module';
import { OperatorWorkShiftComponent } from 'app/entities/operator-work-shift/operator-work-shift.component';
import { OperatorWorkShiftService } from 'app/entities/operator-work-shift/operator-work-shift.service';
import { OperatorWorkShift } from 'app/shared/model/operator-work-shift.model';

describe('Component Tests', () => {
  describe('OperatorWorkShift Management Component', () => {
    let comp: OperatorWorkShiftComponent;
    let fixture: ComponentFixture<OperatorWorkShiftComponent>;
    let service: OperatorWorkShiftService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [OperatorWorkShiftComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(OperatorWorkShiftComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OperatorWorkShiftComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OperatorWorkShiftService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OperatorWorkShift(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.operatorWorkShifts && comp.operatorWorkShifts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OperatorWorkShift(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.operatorWorkShifts && comp.operatorWorkShifts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
