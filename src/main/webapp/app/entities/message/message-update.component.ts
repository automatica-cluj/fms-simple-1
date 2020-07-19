import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMessage, Message } from 'app/shared/model/message.model';
import { MessageService } from './message.service';
import { IOperatorWorkShift } from 'app/shared/model/operator-work-shift.model';
import { OperatorWorkShiftService } from 'app/entities/operator-work-shift/operator-work-shift.service';

@Component({
  selector: 'bpf-message-update',
  templateUrl: './message-update.component.html',
})
export class MessageUpdateComponent implements OnInit {
  isSaving = false;
  operatorworkshifts: IOperatorWorkShift[] = [];

  editForm = this.fb.group({
    id: [],
    subject: [],
    content: [],
    status: [],
    operatorWorkShiftId: [],
  });

  constructor(
    protected messageService: MessageService,
    protected operatorWorkShiftService: OperatorWorkShiftService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ message }) => {
      this.updateForm(message);

      this.operatorWorkShiftService
        .query()
        .subscribe((res: HttpResponse<IOperatorWorkShift[]>) => (this.operatorworkshifts = res.body || []));
    });
  }

  updateForm(message: IMessage): void {
    this.editForm.patchValue({
      id: message.id,
      subject: message.subject,
      content: message.content,
      status: message.status,
      operatorWorkShiftId: message.operatorWorkShiftId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const message = this.createFromForm();
    if (message.id !== undefined) {
      this.subscribeToSaveResponse(this.messageService.update(message));
    } else {
      this.subscribeToSaveResponse(this.messageService.create(message));
    }
  }

  private createFromForm(): IMessage {
    return {
      ...new Message(),
      id: this.editForm.get(['id'])!.value,
      subject: this.editForm.get(['subject'])!.value,
      content: this.editForm.get(['content'])!.value,
      status: this.editForm.get(['status'])!.value,
      operatorWorkShiftId: this.editForm.get(['operatorWorkShiftId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMessage>>): void {
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
