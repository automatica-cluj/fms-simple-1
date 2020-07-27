import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProductionArea } from 'app/shared/model/production-area.model';
import { ProductionAreaService } from './production-area.service';
import { ProductionAreaDeleteDialogComponent } from './production-area-delete-dialog.component';

@Component({
  selector: 'bpf-production-area',
  templateUrl: './production-area.component.html',
})
export class ProductionAreaComponent implements OnInit, OnDestroy {
  productionAreas?: IProductionArea[];
  eventSubscriber?: Subscription;

  constructor(
    protected productionAreaService: ProductionAreaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.productionAreaService.query().subscribe((res: HttpResponse<IProductionArea[]>) => (this.productionAreas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProductionAreas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProductionArea): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProductionAreas(): void {
    this.eventSubscriber = this.eventManager.subscribe('productionAreaListModification', () => this.loadAll());
  }

  delete(productionArea: IProductionArea): void {
    const modalRef = this.modalService.open(ProductionAreaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.productionArea = productionArea;
  }
}
