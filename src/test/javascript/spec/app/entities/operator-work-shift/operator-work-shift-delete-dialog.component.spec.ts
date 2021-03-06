import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IfmSimple1TestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { OperatorWorkShiftDeleteDialogComponent } from 'app/entities/operator-work-shift/operator-work-shift-delete-dialog.component';
import { OperatorWorkShiftService } from 'app/entities/operator-work-shift/operator-work-shift.service';

describe('Component Tests', () => {
  describe('OperatorWorkShift Management Delete Component', () => {
    let comp: OperatorWorkShiftDeleteDialogComponent;
    let fixture: ComponentFixture<OperatorWorkShiftDeleteDialogComponent>;
    let service: OperatorWorkShiftService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IfmSimple1TestModule],
        declarations: [OperatorWorkShiftDeleteDialogComponent],
      })
        .overrideTemplate(OperatorWorkShiftDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OperatorWorkShiftDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OperatorWorkShiftService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
