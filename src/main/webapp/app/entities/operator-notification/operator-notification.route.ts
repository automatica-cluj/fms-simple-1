import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOperatorNotification, OperatorNotification } from 'app/shared/model/operator-notification.model';
import { OperatorNotificationService } from './operator-notification.service';
import { OperatorNotificationComponent } from './operator-notification.component';
import { OperatorNotificationDetailComponent } from './operator-notification-detail.component';
import { OperatorNotificationUpdateComponent } from './operator-notification-update.component';

@Injectable({ providedIn: 'root' })
export class OperatorNotificationResolve implements Resolve<IOperatorNotification> {
  constructor(private service: OperatorNotificationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOperatorNotification> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((operatorNotification: HttpResponse<OperatorNotification>) => {
          if (operatorNotification.body) {
            return of(operatorNotification.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OperatorNotification());
  }
}

export const operatorNotificationRoute: Routes = [
  {
    path: '',
    component: OperatorNotificationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.operatorNotification.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OperatorNotificationDetailComponent,
    resolve: {
      operatorNotification: OperatorNotificationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.operatorNotification.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OperatorNotificationUpdateComponent,
    resolve: {
      operatorNotification: OperatorNotificationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.operatorNotification.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OperatorNotificationUpdateComponent,
    resolve: {
      operatorNotification: OperatorNotificationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ifmSimple1App.operatorNotification.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
