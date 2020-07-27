import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IfmSimple1TestModule } from '../../../test.module';
import { FactoryEventUpdateComponent } from 'app/entities/factory-event/factory-event-update.component';
import { FactoryEventService } from 'app/entities/factory-event/factory-event.service';
import { FactoryEvent } from 'app/shared/model/factory-event.model';

describe('Component Tests', () => {
  describe('FactoryEvent Management Update Component', () => {
    let comp: FactoryEventUpdateComponent;
    let fixture: ComponentFixture<FactoryEventUpdateComponent>;
    let service: FactoryEventService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [FactoryEventUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(FactoryEventUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FactoryEventUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FactoryEventService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FactoryEvent(123);
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
        const entity = new FactoryEvent();
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
