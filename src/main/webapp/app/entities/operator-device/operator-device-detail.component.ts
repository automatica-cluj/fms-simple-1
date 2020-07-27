import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOperatorDevice } from 'app/shared/model/operator-device.model';

@Component({
  selector: 'bpf-operator-device-detail',
  templateUrl: './operator-device-detail.component.html',
})
export class OperatorDeviceDetailComponent implements OnInit {
  operatorDevice: IOperatorDevice | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operatorDevice }) => (this.operatorDevice = operatorDevice));
  }

  previousState(): void {
    window.history.back();
  }
}
