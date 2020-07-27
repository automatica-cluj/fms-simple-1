import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOperatorDevice, OperatorDevice } from 'app/shared/model/operator-device.model';
import { OperatorDeviceService } from './operator-device.service';

@Component({
  selector: 'bpf-operator-device-update',
  templateUrl: './operator-device-update.component.html',
})
export class OperatorDeviceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    type: [],
    registrationId: [],
  });

  constructor(protected operatorDeviceService: OperatorDeviceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operatorDevice }) => {
      this.updateForm(operatorDevice);
    });
  }

  updateForm(operatorDevice: IOperatorDevice): void {
    this.editForm.patchValue({
      id: operatorDevice.id,
      name: operatorDevice.name,
      type: operatorDevice.type,
      registrationId: operatorDevice.registrationId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const operatorDevice = this.createFromForm();
    if (operatorDevice.id !== undefined) {
      this.subscribeToSaveResponse(this.operatorDeviceService.update(operatorDevice));
    } else {
      this.subscribeToSaveResponse(this.operatorDeviceService.create(operatorDevice));
    }
  }

  private createFromForm(): IOperatorDevice {
    return {
      ...new OperatorDevice(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      type: this.editForm.get(['type'])!.value,
      registrationId: this.editForm.get(['registrationId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperatorDevice>>): void {
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
}
