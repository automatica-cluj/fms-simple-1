import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDevice } from 'app/shared/model/device.model';

@Component({
  selector: 'bpf-device-detail',
  templateUrl: './device-detail.component.html',
})
export class DeviceDetailComponent implements OnInit {
  device: IDevice | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ device }) => (this.device = device));
  }

  previousState(): void {
    window.history.back();
  }
}
