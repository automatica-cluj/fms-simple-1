import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOperatorWorkShift } from 'app/shared/model/operator-work-shift.model';

@Component({
  selector: 'bpf-operator-work-shift-detail',
  templateUrl: './operator-work-shift-detail.component.html',
})
export class OperatorWorkShiftDetailComponent implements OnInit {
  operatorWorkShift: IOperatorWorkShift | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operatorWorkShift }) => (this.operatorWorkShift = operatorWorkShift));
  }

  previousState(): void {
    window.history.back();
  }
}
