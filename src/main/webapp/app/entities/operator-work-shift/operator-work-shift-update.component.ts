import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOperatorWorkShift, OperatorWorkShift } from 'app/shared/model/operator-work-shift.model';
import { OperatorWorkShiftService } from './operator-work-shift.service';
import { IDevice } from 'app/shared/model/device.model';
import { DeviceService } from 'app/entities/device/device.service';
import { IOperator } from 'app/shared/model/operator.model';
import { OperatorService } from 'app/entities/operator/operator.service';

type SelectableEntity = IDevice | IOperator;

@Component({
  selector: 'bpf-operator-work-shift-update',
  templateUrl: './operator-work-shift-update.component.html',
})
export class OperatorWorkShiftUpdateComponent implements OnInit {
  isSaving = false;
  devices: IDevice[] = [];
  operators: IOperator[] = [];

  editForm = this.fb.group({
    id: [],
    location: [],
    startDate: [],
    endDate: [],
    deviceId: [],
    operatorId: [],
  });

  constructor(
    protected operatorWorkShiftService: OperatorWorkShiftService,
    protected deviceService: DeviceService,
    protected operatorService: OperatorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operatorWorkShift }) => {
      if (!operatorWorkShift.id) {
        const today = moment().startOf('day');
        operatorWorkShift.startDate = today;
        operatorWorkShift.endDate = today;
      }

      this.updateForm(operatorWorkShift);

      this.deviceService
        .query({ filter: 'operatorworkshift-is-null' })
        .pipe(
          map((res: HttpResponse<IDevice[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IDevice[]) => {
          if (!operatorWorkShift.deviceId) {
            this.devices = resBody;
          } else {
            this.deviceService
              .find(operatorWorkShift.deviceId)
              .pipe(
                map((subRes: HttpResponse<IDevice>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IDevice[]) => (this.devices = concatRes));
          }
        });

      this.operatorService
        .query({ filter: 'operatorworkshift-is-null' })
        .pipe(
          map((res: HttpResponse<IOperator[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IOperator[]) => {
          if (!operatorWorkShift.operatorId) {
            this.operators = resBody;
          } else {
            this.operatorService
              .find(operatorWorkShift.operatorId)
              .pipe(
                map((subRes: HttpResponse<IOperator>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IOperator[]) => (this.operators = concatRes));
          }
        });
    });
  }

  updateForm(operatorWorkShift: IOperatorWorkShift): void {
    this.editForm.patchValue({
      id: operatorWorkShift.id,
      location: operatorWorkShift.location,
      startDate: operatorWorkShift.startDate ? operatorWorkShift.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: operatorWorkShift.endDate ? operatorWorkShift.endDate.format(DATE_TIME_FORMAT) : null,
      deviceId: operatorWorkShift.deviceId,
      operatorId: operatorWorkShift.operatorId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const operatorWorkShift = this.createFromForm();
    if (operatorWorkShift.id !== undefined) {
      this.subscribeToSaveResponse(this.operatorWorkShiftService.update(operatorWorkShift));
    } else {
      this.subscribeToSaveResponse(this.operatorWorkShiftService.create(operatorWorkShift));
    }
  }

  private createFromForm(): IOperatorWorkShift {
    return {
      ...new OperatorWorkShift(),
      id: this.editForm.get(['id'])!.value,
      location: this.editForm.get(['location'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      deviceId: this.editForm.get(['deviceId'])!.value,
      operatorId: this.editForm.get(['operatorId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperatorWorkShift>>): void {
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
