import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IfmSimple1TestModule } from '../../../test.module';
import { OperatorDeviceUpdateComponent } from 'app/entities/operator-device/operator-device-update.component';
import { OperatorDeviceService } from 'app/entities/operator-device/operator-device.service';
import { OperatorDevice } from 'app/shared/model/operator-device.model';

describe('Component Tests', () => {
  describe('OperatorDevice Management Update Component', () => {
    let comp: OperatorDeviceUpdateComponent;
    let fixture: ComponentFixture<OperatorDeviceUpdateComponent>;
    let service: OperatorDeviceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [OperatorDeviceUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OperatorDeviceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OperatorDeviceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OperatorDeviceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OperatorDevice(123);
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
        const entity = new OperatorDevice();
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
