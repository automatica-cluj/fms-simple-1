import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IfmSimple1TestModule } from '../../../test.module';
import { OperatorWorkShiftUpdateComponent } from 'app/entities/operator-work-shift/operator-work-shift-update.component';
import { OperatorWorkShiftService } from 'app/entities/operator-work-shift/operator-work-shift.service';
import { OperatorWorkShift } from 'app/shared/model/operator-work-shift.model';

describe('Component Tests', () => {
  describe('OperatorWorkShift Management Update Component', () => {
    let comp: OperatorWorkShiftUpdateComponent;
    let fixture: ComponentFixture<OperatorWorkShiftUpdateComponent>;
    let service: OperatorWorkShiftService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [OperatorWorkShiftUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OperatorWorkShiftUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OperatorWorkShiftUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OperatorWorkShiftService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OperatorWorkShift(123);
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
        const entity = new OperatorWorkShift();
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
