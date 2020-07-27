import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOperatorNotification, OperatorNotification } from 'app/shared/model/operator-notification.model';
import { OperatorNotificationService } from './operator-notification.service';
import { IOperatorWorkShift } from 'app/shared/model/operator-work-shift.model';
import { OperatorWorkShiftService } from 'app/entities/operator-work-shift/operator-work-shift.service';
import { IFactoryEvent } from 'app/shared/model/factory-event.model';
import { FactoryEventService } from 'app/entities/factory-event/factory-event.service';

type SelectableEntity = IOperatorWorkShift | IFactoryEvent;

@Component({
  selector: 'bpf-operator-notification-update',
  templateUrl: './operator-notification-update.component.html',
})
export class OperatorNotificationUpdateComponent implements OnInit {
  isSaving = false;
  operatorworkshifts: IOperatorWorkShift[] = [];
  factoryevents: IFactoryEvent[] = [];

  editForm = this.fb.group({
    id: [],
    receiveDate: [],
    responseDate: [],
    operatorResponse: [],
    operatorWorkShiftId: [],
    factoryEventId: [],
  });

  constructor(
    protected operatorNotificationService: OperatorNotificationService,
    protected operatorWorkShiftService: OperatorWorkShiftService,
    protected factoryEventService: FactoryEventService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operatorNotification }) => {
      if (!operatorNotification.id) {
        const today = moment().startOf('day');
        operatorNotification.receiveDate = today;
        operatorNotification.responseDate = today;
      }

      this.updateForm(operatorNotification);

      this.operatorWorkShiftService
        .query()
        .subscribe((res: HttpResponse<IOperatorWorkShift[]>) => (this.operatorworkshifts = res.body || []));

      this.factoryEventService.query().subscribe((res: HttpResponse<IFactoryEvent[]>) => (this.factoryevents = res.body || []));
    });
  }

  updateForm(operatorNotification: IOperatorNotification): void {
    this.editForm.patchValue({
      id: operatorNotification.id,
      receiveDate: operatorNotification.receiveDate ? operatorNotification.receiveDate.format(DATE_TIME_FORMAT) : null,
      responseDate: operatorNotification.responseDate ? operatorNotification.responseDate.format(DATE_TIME_FORMAT) : null,
      operatorResponse: operatorNotification.operatorResponse,
      operatorWorkShiftId: operatorNotification.operatorWorkShiftId,
      factoryEventId: operatorNotification.factoryEventId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const operatorNotification = this.createFromForm();
    if (operatorNotification.id !== undefined) {
      this.subscribeToSaveResponse(this.operatorNotificationService.update(operatorNotification));
    } else {
      this.subscribeToSaveResponse(this.operatorNotificationService.create(operatorNotification));
    }
  }

  private createFromForm(): IOperatorNotification {
    return {
      ...new OperatorNotification(),
      id: this.editForm.get(['id'])!.value,
      receiveDate: this.editForm.get(['receiveDate'])!.value
        ? moment(this.editForm.get(['receiveDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      responseDate: this.editForm.get(['responseDate'])!.value
        ? moment(this.editForm.get(['responseDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      operatorResponse: this.editForm.get(['operatorResponse'])!.value,
      operatorWorkShiftId: this.editForm.get(['operatorWorkShiftId'])!.value,
      factoryEventId: this.editForm.get(['factoryEventId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperatorNotification>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
