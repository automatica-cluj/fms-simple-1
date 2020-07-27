import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductionArea } from 'app/shared/model/production-area.model';
import { ProductionAreaService } from './production-area.service';

@Component({
  templateUrl: './production-area-delete-dialog.component.html',
})
export class ProductionAreaDeleteDialogComponent {
  productionArea?: IProductionArea;

  constructor(
    protected productionAreaService: ProductionAreaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.productionAreaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productionAreaListModification');
      this.activeModal.close();
    });
  }
}
