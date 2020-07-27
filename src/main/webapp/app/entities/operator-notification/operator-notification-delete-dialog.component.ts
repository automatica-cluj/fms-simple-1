import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOperatorNotification } from 'app/shared/model/operator-notification.model';
import { OperatorNotificationService } from './operator-notification.service';

@Component({
  templateUrl: './operator-notification-delete-dialog.component.html',
})
export class OperatorNotificationDeleteDialogComponent {
  operatorNotification?: IOperatorNotification;

  constructor(
    protected operatorNotificationService: OperatorNotificationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.operatorNotificationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('operatorNotificationListModification');
      this.activeModal.close();
    });
  }
}
