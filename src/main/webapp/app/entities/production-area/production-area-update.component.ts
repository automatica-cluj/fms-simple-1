import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProductionArea, ProductionArea } from 'app/shared/model/production-area.model';
import { ProductionAreaService } from './production-area.service';
import { IOperatorWorkShift } from 'app/shared/model/operator-work-shift.model';
import { OperatorWorkShiftService } from 'app/entities/operator-work-shift/operator-work-shift.service';

@Component({
  selector: 'bpf-production-area-update',
  templateUrl: './production-area-update.component.html',
})
export class ProductionAreaUpdateComponent implements OnInit {
  isSaving = false;
  operatorworkshifts: IOperatorWorkShift[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    operatorWorkShiftId: [],
  });

  constructor(
    protected productionAreaService: ProductionAreaService,
    protected operatorWorkShiftService: OperatorWorkShiftService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productionArea }) => {
      this.updateForm(productionArea);

      this.operatorWorkShiftService
        .query()
        .subscribe((res: HttpResponse<IOperatorWorkShift[]>) => (this.operatorworkshifts = res.body || []));
    });
  }

  updateForm(productionArea: IProductionArea): void {
    this.editForm.patchValue({
      id: productionArea.id,
      name: productionArea.name,
      operatorWorkShiftId: productionArea.operatorWorkShiftId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productionArea = this.createFromForm();
    if (productionArea.id !== undefined) {
      this.subscribeToSaveResponse(this.productionAreaService.update(productionArea));
    } else {
      this.subscribeToSaveResponse(this.productionAreaService.create(productionArea));
    }
  }

  private createFromForm(): IProductionArea {
    return {
      ...new ProductionArea(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      operatorWorkShiftId: this.editForm.get(['operatorWorkShiftId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductionArea>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IOperatorWorkShift): any {
    return item.id;
  }
}
