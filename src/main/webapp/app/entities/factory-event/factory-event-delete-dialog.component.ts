import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFactoryEvent } from 'app/shared/model/factory-event.model';
import { FactoryEventService } from './factory-event.service';

@Component({
  templateUrl: './factory-event-delete-dialog.component.html',
})
export class FactoryEventDeleteDialogComponent {
  factoryEvent?: IFactoryEvent;

  constructor(
    protected factoryEventService: FactoryEventService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.factoryEventService.delete(id).subscribe(() => {
      this.eventManager.broadcast('factoryEventListModification');
      this.activeModal.close();
    });
  }
}
