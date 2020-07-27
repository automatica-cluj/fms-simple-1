import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { IfmSimple1TestModule } from '../../../test.module';
import { OperatorDeviceComponent } from 'app/entities/operator-device/operator-device.component';
import { OperatorDeviceService } from 'app/entities/operator-device/operator-device.service';
import { OperatorDevice } from 'app/shared/model/operator-device.model';

describe('Component Tests', () => {
  describe('OperatorDevice Management Component', () => {
    let comp: OperatorDeviceComponent;
    let fixture: ComponentFixture<OperatorDeviceComponent>;
    let service: OperatorDeviceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [OperatorDeviceComponent],
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
        .overrideTemplate(OperatorDeviceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OperatorDeviceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OperatorDeviceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OperatorDevice(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.operatorDevices && comp.operatorDevices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new OperatorDevice(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.operatorDevices && comp.operatorDevices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
