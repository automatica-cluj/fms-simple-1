import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { IfmSimple1TestModule } from '../../../test.module';
import { FactoryEventComponent } from 'app/entities/factory-event/factory-event.component';
import { FactoryEventService } from 'app/entities/factory-event/factory-event.service';
import { FactoryEvent } from 'app/shared/model/factory-event.model';

describe('Component Tests', () => {
  describe('FactoryEvent Management Component', () => {
    let comp: FactoryEventComponent;
    let fixture: ComponentFixture<FactoryEventComponent>;
    let service: FactoryEventService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [FactoryEventComponent],
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
        .overrideTemplate(FactoryEventComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FactoryEventComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FactoryEventService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FactoryEvent(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.factoryEvents && comp.factoryEvents[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FactoryEvent(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.factoryEvents && comp.factoryEvents[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
