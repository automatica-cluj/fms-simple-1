import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOperatorNotification } from 'app/shared/model/operator-notification.model';

@Component({
  selector: 'bpf-operator-notification-detail',
  templateUrl: './operator-notification-detail.component.html',
})
export class OperatorNotificationDetailComponent implements OnInit {
  operatorNotification: IOperatorNotification | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operatorNotification }) => (this.operatorNotification = operatorNotification));
  }

  previousState(): void {
    window.history.back();
  }
}
