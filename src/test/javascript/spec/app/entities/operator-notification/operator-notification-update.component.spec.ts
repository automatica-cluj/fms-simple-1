import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IfmSimple1TestModule } from '../../../test.module';
import { OperatorNotificationUpdateComponent } from 'app/entities/operator-notification/operator-notification-update.component';
import { OperatorNotificationService } from 'app/entities/operator-notification/operator-notification.service';
import { OperatorNotification } from 'app/shared/model/operator-notification.model';

describe('Component Tests', () => {
  describe('OperatorNotification Management Update Component', () => {
    let comp: OperatorNotificationUpdateComponent;
    let fixture: ComponentFixture<OperatorNotificationUpdateComponent>;
    let service: OperatorNotificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [OperatorNotificationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OperatorNotificationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OperatorNotificationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OperatorNotificationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OperatorNotification(123);
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
        const entity = new OperatorNotification();
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
