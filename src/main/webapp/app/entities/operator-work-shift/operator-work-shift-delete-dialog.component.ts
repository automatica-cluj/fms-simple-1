import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOperatorWorkShift } from 'app/shared/model/operator-work-shift.model';
import { OperatorWorkShiftService } from './operator-work-shift.service';

@Component({
  templateUrl: './operator-work-shift-delete-dialog.component.html',
})
export class OperatorWorkShiftDeleteDialogComponent {
  operatorWorkShift?: IOperatorWorkShift;

  constructor(
    protected operatorWorkShiftService: OperatorWorkShiftService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.operatorWorkShiftService.delete(id).subscribe(() => {
      this.eventManager.broadcast('operatorWorkShiftListModification');
      this.activeModal.close();
    });
  }
}
