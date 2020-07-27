import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOperatorDevice } from 'app/shared/model/operator-device.model';
import { OperatorDeviceService } from './operator-device.service';

@Component({
  templateUrl: './operator-device-delete-dialog.component.html',
})
export class OperatorDeviceDeleteDialogComponent {
  operatorDevice?: IOperatorDevice;

  constructor(
    protected operatorDeviceService: OperatorDeviceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.operatorDeviceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('operatorDeviceListModification');
      this.activeModal.close();
    });
  }
}
