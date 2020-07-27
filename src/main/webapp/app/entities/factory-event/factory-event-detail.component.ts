import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFactoryEvent } from 'app/shared/model/factory-event.model';

@Component({
  selector: 'bpf-factory-event-detail',
  templateUrl: './factory-event-detail.component.html',
})
export class FactoryEventDetailComponent implements OnInit {
  factoryEvent: IFactoryEvent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ factoryEvent }) => (this.factoryEvent = factoryEvent));
  }

  previousState(): void {
    window.history.back();
  }
}
