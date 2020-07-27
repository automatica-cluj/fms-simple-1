import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IfmSimple1TestModule } from '../../../test.module';
import { ProductionAreaComponent } from 'app/entities/production-area/production-area.component';
import { ProductionAreaService } from 'app/entities/production-area/production-area.service';
import { ProductionArea } from 'app/shared/model/production-area.model';

describe('Component Tests', () => {
  describe('ProductionArea Management Component', () => {
    let comp: ProductionAreaComponent;
    let fixture: ComponentFixture<ProductionAreaComponent>;
    let service: ProductionAreaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [ProductionAreaComponent],
      })
        .overrideTemplate(ProductionAreaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductionAreaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductionAreaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProductionArea(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.productionAreas && comp.productionAreas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
