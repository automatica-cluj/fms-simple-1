import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFactoryEvent, FactoryEvent } from 'app/shared/model/factory-event.model';
import { FactoryEventService } from './factory-event.service';
import { IProductionArea } from 'app/shared/model/production-area.model';
import { ProductionAreaService } from 'app/entities/production-area/production-area.service';

@Component({
  selector: 'bpf-factory-event-update',
  templateUrl: './factory-event-update.component.html',
})
export class FactoryEventUpdateComponent implements OnInit {
  isSaving = false;
  productionareas: IProductionArea[] = [];

  editForm = this.fb.group({
    id: [],
    subject: [],
    body: [],
    type: [],
    createDate: [],
    notificationCount: [],
    nextNotificationDate: [],
    processingStatus: [],
    productionAreaId: [null, Validators.required],
  });

  constructor(
    protected factoryEventService: FactoryEventService,
    protected productionAreaService: ProductionAreaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ factoryEvent }) => {
      if (!factoryEvent.id) {
        const today = moment().startOf('day');
        factoryEvent.createDate = today;
        factoryEvent.nextNotificationDate = today;
      }

      this.updateForm(factoryEvent);

      this.productionAreaService
        .query({ filter: 'factoryevent-is-null' })
        .pipe(
          map((res: HttpResponse<IProductionArea[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProductionArea[]) => {
          if (!factoryEvent.productionAreaId) {
            this.productionareas = resBody;
          } else {
            this.productionAreaService
              .find(factoryEvent.productionAreaId)
              .pipe(
                map((subRes: HttpResponse<IProductionArea>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProductionArea[]) => (this.productionareas = concatRes));
          }
        });
    });
  }

  updateForm(factoryEvent: IFactoryEvent): void {
    this.editForm.patchValue({
      id: factoryEvent.id,
      subject: factoryEvent.subject,
      body: factoryEvent.body,
      type: factoryEvent.type,
      createDate: factoryEvent.createDate ? factoryEvent.createDate.format(DATE_TIME_FORMAT) : null,
      notificationCount: factoryEvent.notificationCount,
      nextNotificationDate: factoryEvent.nextNotificationDate ? factoryEvent.nextNotificationDate.format(DATE_TIME_FORMAT) : null,
      processingStatus: factoryEvent.processingStatus,
      productionAreaId: factoryEvent.productionAreaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const factoryEvent = this.createFromForm();
    if (factoryEvent.id !== undefined) {
      this.subscribeToSaveResponse(this.factoryEventService.update(factoryEvent));
    } else {
      this.subscribeToSaveResponse(this.factoryEventService.create(factoryEvent));
    }
  }

  private createFromForm(): IFactoryEvent {
    return {
      ...new FactoryEvent(),
      id: this.editForm.get(['id'])!.value,
      subject: this.editForm.get(['subject'])!.value,
      body: this.editForm.get(['body'])!.value,
      type: this.editForm.get(['type'])!.value,
      createDate: this.editForm.get(['createDate'])!.value ? moment(this.editForm.get(['createDate'])!.value, DATE_TIME_FORMAT) : undefined,
      notificationCount: this.editForm.get(['notificationCount'])!.value,
      nextNotificationDate: this.editForm.get(['nextNotificationDate'])!.value
        ? moment(this.editForm.get(['nextNotificationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      processingStatus: this.editForm.get(['processingStatus'])!.value,
      productionAreaId: this.editForm.get(['productionAreaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFactoryEvent>>): void {
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

  trackById(index: number, item: IProductionArea): any {
    return item.id;
  }
}
