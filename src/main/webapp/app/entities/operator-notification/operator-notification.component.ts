import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOperatorNotification } from 'app/shared/model/operator-notification.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { OperatorNotificationService } from './operator-notification.service';
import { OperatorNotificationDeleteDialogComponent } from './operator-notification-delete-dialog.component';

@Component({
  selector: 'bpf-operator-notification',
  templateUrl: './operator-notification.component.html',
})
export class OperatorNotificationComponent implements OnInit, OnDestroy {
  operatorNotifications: IOperatorNotification[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected operatorNotificationService: OperatorNotificationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.operatorNotifications = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.operatorNotificationService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IOperatorNotification[]>) => this.paginateOperatorNotifications(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.operatorNotifications = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOperatorNotifications();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOperatorNotification): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOperatorNotifications(): void {
    this.eventSubscriber = this.eventManager.subscribe('operatorNotificationListModification', () => this.reset());
  }

  delete(operatorNotification: IOperatorNotification): void {
    const modalRef = this.modalService.open(OperatorNotificationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.operatorNotification = operatorNotification;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateOperatorNotifications(data: IOperatorNotification[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.operatorNotifications.push(data[i]);
      }
    }
  }
}
