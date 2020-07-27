import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IfmSimple1TestModule } from '../../../test.module';
import { ProductionAreaUpdateComponent } from 'app/entities/production-area/production-area-update.component';
import { ProductionAreaService } from 'app/entities/production-area/production-area.service';
import { ProductionArea } from 'app/shared/model/production-area.model';

describe('Component Tests', () => {
  describe('ProductionArea Management Update Component', () => {
    let comp: ProductionAreaUpdateComponent;
    let fixture: ComponentFixture<ProductionAreaUpdateComponent>;
    let service: ProductionAreaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [ProductionAreaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProductionAreaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductionAreaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductionAreaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductionArea(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductionArea();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
