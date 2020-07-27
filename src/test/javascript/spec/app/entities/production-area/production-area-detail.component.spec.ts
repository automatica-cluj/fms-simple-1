import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IfmSimple1TestModule } from '../../../test.module';
import { ProductionAreaDetailComponent } from 'app/entities/production-area/production-area-detail.component';
import { ProductionArea } from 'app/shared/model/production-area.model';

describe('Component Tests', () => {
  describe('ProductionArea Management Detail Component', () => {
    let comp: ProductionAreaDetailComponent;
    let fixture: ComponentFixture<ProductionAreaDetailComponent>;
    const route = ({ data: of({ productionArea: new ProductionArea(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [ProductionAreaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProductionAreaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductionAreaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load productionArea on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productionArea).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
