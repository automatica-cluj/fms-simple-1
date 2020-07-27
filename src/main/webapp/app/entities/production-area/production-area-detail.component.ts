import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductionArea } from 'app/shared/model/production-area.model';

@Component({
  selector: 'bpf-production-area-detail',
  templateUrl: './production-area-detail.component.html',
})
export class ProductionAreaDetailComponent implements OnInit {
  productionArea: IProductionArea | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productionArea }) => (this.productionArea = productionArea));
  }

  previousState(): void {
    window.history.back();
  }
}
